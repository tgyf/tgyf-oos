package com.tgyf.oss.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.tgyf.oss.BaseOssProcessor;
import com.tgyf.oss.TencentOssProcessorImpl;
import com.tgyf.oss.handler.OssHandler;
import com.tgyf.oss.properties.TencentOssProperty;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(value = "tgyf.oss.type", havingValue = "TENCENT")
@AutoConfigureAfter({OssConfig.class})
@ConditionalOnBean(OssHandler.class)
@ConditionalOnMissingBean({BaseOssProcessor.class})
@EnableConfigurationProperties({TencentOssProperty.class})
public class TencentOssConfig {
    @Resource
    private TencentOssProperty tencentOssProperty;

    /**
     * 腾讯云OSS客户端生成
     *
     * @return 腾讯云OSS客户端
     */
    @Bean
    public COSClient cosClient() {
        // 初始化永久密钥信息
        COSCredentials cred = new BasicCOSCredentials(tencentOssProperty.getAccessKeyId(), tencentOssProperty.getAccessKeySecret());
        Region region = new Region(tencentOssProperty.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    @Bean
    public TencentOssProcessorImpl tencentOssProcessorImpl() {
        TencentOssProcessorImpl tencentOssProcessor = new TencentOssProcessorImpl(cosClient(), tencentOssProperty);
        return tencentOssProcessor;
    }
}
