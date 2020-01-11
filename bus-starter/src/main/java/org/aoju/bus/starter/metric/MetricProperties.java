/*
 * The MIT License
 *
 * Copyright (c) 2015-2020 aoju.org All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aoju.bus.starter.metric;

import lombok.Data;
import org.aoju.bus.starter.BusXExtend;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * API网关配置信息
 *
 * @author Kimi Liu
 * @version 5.5.2
 * @since JDK 1.8++
 */
@Data
@ConfigurationProperties(prefix = BusXExtend.METRIC)
public class MetricProperties {

    /**
     * API标示
     */
    private String appKey;
    /**
     * API密钥
     */
    private String appSecret;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 版本号
     */
    private String version;
    /**
     * Netty启动端口
     */
    private String port;
    /**
     * 是否签名/加解密
     */
    private String sign;
    /**
     * 超时时间
     */
    private int timeout = 3;
    /**
     * 拦截器
     */
    private List<String> handlers;

}