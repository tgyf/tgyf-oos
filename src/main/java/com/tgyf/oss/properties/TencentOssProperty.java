package com.tgyf.oss.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Data
@ConfigurationProperties(prefix = "tgyf.oss.tencent")
public class TencentOssProperty {
    /**
     * 腾讯云OSS的region
     */
    @NotBlank(message = "'tgyf.oss.tencent.region' property can not be null.please find it in your tencent.")
    public String region  = "ap-guangzhou";
    /**
     * 腾讯云RAM-AccessKeyId
     */
    @NotBlank(message = "'tgyf.oss.tencent.accessKey' property can not be null.please find it in your tencent.")
    private String accessKeyId;
    /**
     * 腾讯云RAM-AccessKeySecret
     */
    @NotBlank(message = "'tgyf.oss.tencent.accessSecret' property can not be null.please find it in your tencent.")
    private String accessKeySecret;
    /**
     * 腾讯云oss-bucketname
     */
    @NotBlank(message = "'tgyf.oss.tencent.bucket' property can not be null.please find it in your tencent.")
    private String bucket;
    /**
     * 腾讯云OSS签名过期时间
     */
    @NotNull(message = "'tgyf.oss.tencent.signatureExpireSeconds' property can not be null.please set it.")
    private Integer signatureExpireSeconds;
}
