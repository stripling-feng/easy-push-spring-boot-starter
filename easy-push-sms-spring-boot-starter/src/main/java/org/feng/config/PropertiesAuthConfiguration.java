package org.feng.config;

import lombok.extern.slf4j.Slf4j;
import org.feng.properties.SmsAliProperties;
import org.feng.properties.SmsAsyncProperties;
import org.feng.properties.SmsProperties;
import org.feng.properties.SmsTencentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author feng
 * yaml 配置自动配置类
 */
@Configuration
@Slf4j
@Order(-1)
public class PropertiesAuthConfiguration {

    @Bean
    public SmsAliProperties smsAliProperties() {
        return new SmsAliProperties();
    }

    @Bean
    public SmsProperties smsProperties() {
        return new SmsProperties();
    }

    @Bean
    public SmsAsyncProperties smsAsyncProperties() {
        return new SmsAsyncProperties();
    }

    @Bean
    public SmsTencentProperties smsTencentProperties() {
        return new SmsTencentProperties();
    }
}
