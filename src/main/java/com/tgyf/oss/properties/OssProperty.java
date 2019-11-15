package com.tgyf.oss.properties;

import com.tgyf.oss.constant.OssType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tgyf.oss")
public class OssProperty {

    /**
     * 是否开启OSS
     */
    private boolean enable = false;
    /**
     * OSS 服务商类型，目前支持四种:阿里云，腾讯云，七牛云与自定义
     */
    private OssType type;
}
