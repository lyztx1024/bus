package org.aoju.bus.trace4j;

import org.aoju.bus.trace4j.config.TraceFilterConfiguration;
import org.aoju.bus.trace4j.consts.TraceConsts;

import java.util.Map;

/**
 * 后端应该是线程安全的(读写被委托给线程本地状态).
 */
public interface TraceBackend {

    TraceFilterConfiguration getConfiguration(String profileName);

    TraceFilterConfiguration getConfiguration();

    boolean containsKey(String key);

    String get(String key);

    int size();

    void clear();

    boolean isEmpty();

    void put(String key, String value);

    void putAll(Map<? extends String, ? extends String> m);

    Map<String, String> copyToMap();

    void remove(String key);

    String getInvocationId();

    String getSessionId();

}
