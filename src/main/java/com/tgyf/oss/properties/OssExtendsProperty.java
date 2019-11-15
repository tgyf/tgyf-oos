package com.tgyf.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 扩展属性：
 * 1.redis缓存
 * 2.线程池
 */
@ConfigurationProperties(prefix = "tgyf.oss.extends")
public class OssExtendsProperty {

    /**
     * 是否开启OSS扩展
     */
    private boolean enable = false;

}
