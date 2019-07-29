package org.aoju.bus.spring.druid;

import org.aoju.bus.core.lang.exception.InstrumentException;
import org.aoju.bus.core.utils.StringUtils;
import org.aoju.bus.logger.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

/**
 * 多数据源支持
 *
 * @author aoju.org
 * @version 3.0.1
 * @group 839128
 * @since JDK 1.8
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    private final static ThreadLocal<String> DATA_SOURCE_KEY = new ThreadLocal<>();

    private Set<Object> keySet;

    private static void add(String key) {
        DATA_SOURCE_KEY.set(key);
    }

    private static void clear() {
        DATA_SOURCE_KEY.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DATA_SOURCE_KEY.get();
        if (keySet.contains(key)) {
            Logger.info("this session may use dataSource by key: {}", key);
        }
        return key;
    }

    /**
     * 在获取key的集合，目的只是为了添加一些告警日志
     */
    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        try {
            Field sourceMapField = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
            sourceMapField.setAccessible(true);
            Map<Object, javax.sql.DataSource> sourceMap = (Map<Object, javax.sql.DataSource>) sourceMapField.get(this);
            this.keySet = sourceMap.keySet();
            sourceMapField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new InstrumentException(e);
        }
    }

    @Order(-1)
    @Aspect
    @Component
    public static class DataSourceSwitchInterceptor {

        /**
         * 扫描所有含有@MultiDataSource$DataSource注解的类
         */
        @Pointcut("@annotation(org.aoju.bus.spring.druid.DataSource)")
        public void switching() {
            Logger.info("switch dataSource");
        }

        /**
         * 使用around方式监控
         *
         * @param point
         * @return
         * @throws Throwable
         */
        @Around("switching()")
        public Object switchByMethod(ProceedingJoinPoint point) throws Throwable {
            //获取执行方法
            Method method = getMethodByPoint(point);
            //获取执行参数
            Parameter[] params = method.getParameters();
            String source = null;
            boolean isDynamic = false;
            //扫描是否有参数带有@DataSource注解
            for (int i = params.length - 1; i >= 0; i--) {
                Parameter parameter = params[i];
                if (parameter.getAnnotation(DataSource.class) != null && point.getArgs()[i] instanceof String) {
                    //key值即该参数的值，要求该参数必须为String类型
                    source = (String) point.getArgs()[i];
                    isDynamic = true;
                    break;
                }
            }
            if (!isDynamic) {
                //获取方法的@DataSource注解
                DataSource dataSource = method.getAnnotation(DataSource.class);
                //方法不含有注解
                if (null == dataSource || !StringUtils.hasLength(dataSource.value())) {
                    //获取类级别的@DataSource注解
                    dataSource = method.getDeclaringClass().getAnnotation(DataSource.class);
                }
                if (null != dataSource) {
                    //设置key值
                    source = dataSource.value();
                }
            }
            //继续执行该方法
            return keepOnByByPoint(source, point);
        }


        private Object keepOnByByPoint(String source, ProceedingJoinPoint point) throws Throwable {
            try {
                add(source);
                return point.proceed();
            } finally {
                clear();
            }
        }

        private Method getMethodByPoint(ProceedingJoinPoint point) {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            return methodSignature.getMethod();
        }
    }

}