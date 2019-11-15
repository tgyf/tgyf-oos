package com.tgyf.oss.config;

import com.aliyun.oss.OSSClient;
import com.tgyf.oss.AliyunOssProcessorImpl;
import com.tgyf.oss.BaseOssProcessor;
import com.tgyf.oss.handler.OssHandler;
import com.tgyf.oss.properties.AliyunOssProperty;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(value = "tgyf.oss.type", havingValue = "ALIYUN")
@AutoConfigureAfter({ OssConfig.class })
@ConditionalOnBean(OssHandler.class)
@ConditionalOnMissingBean({ BaseOssProcessor.class })
@EnableConfigurationProperties({ AliyunOssProperty.class })
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

    @Bean
    public AliyunOssProcessorImpl aliyunOssProcessorImpl() {
        AliyunOssProcessorImpl aliyunOssProcessor = new AliyunOssProcessorImpl(ossClient(),aliyunOssProperty);
        return aliyunOssProcessor;
    }
}
