package com.tgyf.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tgyf.oss.extends")
public class OssExtendsProperty {

    /**
     * 是否开启OSS扩展
     */
    private boolean enable = false;
}
