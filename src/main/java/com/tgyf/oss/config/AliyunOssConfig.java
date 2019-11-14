package com.tgyf.oss.config;

import com.aliyun.oss.OSSClient;
import com.tgyf.oss.properties.AliyunOssProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class AliyunOssConfig {
    @Resource
    private AliyunOssProperty aliyunOssProperty;

    /**
     * 阿里云OSS客户端生成
     *
     * @return 阿里云OSS客户端
     */
    @Bean
    public OSSClient ossClient() {
        OSSClient ossClient = new OSSClient(aliyunOssProperty.getEndpoint(), aliyunOssProperty.getAccessKeyId(), aliyunOssProperty.getAccessKeySecret());
        return ossClient;
    }
}
