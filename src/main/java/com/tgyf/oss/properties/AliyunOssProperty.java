package com.tgyf.oss.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Validated
@Data
@ConfigurationProperties(prefix = "tgyf.oss.aliyun")
public class AliyunOssProperty {
    /**
     * 阿里云OSS的endpoint
     */
    @NotBlank(message = "'tgyf.oss.aliyu.endpoint' property can not be null.please find it in your aliyun.")
    public String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    /**
     * 阿里云RAM-AccessKeyId
     */
    @NotBlank(message = "'tgyf.oss.aliyu.accessKey' property can not be null.please find it in your aliyun.")
    private String accessKeyId;
    /**
     * 阿里云RAM-AccessKeySecret
     */
    @NotBlank(message = "'tgyf.oss.aliyu.accessSecret' property can not be null.please find it in your aliyun.")
    private String accessKeySecret;
    /**
     * 阿里云oss-bucketname
     */
    @NotBlank(message = "'tgyf.oss.aliyu.bucket' property can not be null.please find it in your aliyun.")
    private String bucket;
    /**
     * 阿里云OSS签名过期时间
     */
    @NotNull(message = "'tgyf.oss.aliyu.signatureExpireSeconds' property can not be null.please set it.")
    private Integer signatureExpireSeconds;
}
