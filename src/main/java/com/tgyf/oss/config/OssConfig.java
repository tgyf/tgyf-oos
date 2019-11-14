package com.tgyf.oss.config;

import com.tgyf.oss.aop.EnableOssAop;
import com.tgyf.oss.handler.OssHandler;
import com.tgyf.oss.properties.OssExtendsProperty;
import com.tgyf.oss.properties.OssProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OssProperty.class, OssExtendsProperty.class})
@ConditionalOnProperty(name = "tgyf.oss.enable", havingValue = "true", matchIfMissing = true)
public class OssConfig {
    @Autowired
    private OssProperty ossProperty;

    @Bean
    @ConditionalOnProperty(name = "tgyf.oss.type", havingValue = "ALIYUN", matchIfMissing = true)
    @ConditionalOnMissingBean
    public EnableOssAop EnableOssAop(OssHandler ossHandler) {
        EnableOssAop aop = new EnableOssAop(ossHandler);
        return aop;
    }

    /**
     * 后续扩展时需要
     * @param ossExtendsProperty
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public OssHandler exceptionHandler(OssExtendsProperty ossExtendsProperty) {
        OssHandler ossHandler = new OssHandler(ossProperty, ossExtendsProperty);
        return ossHandler;
    }
}
