package org.feng.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feng.cilent.AliSmsClient;
import org.feng.cilent.AsyncClient;
import org.feng.cilent.TencentSmsClient;
import org.feng.properties.SmsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feng
 * 2022/6/24 10:53
 *  TODO
 */
@EnableConfigurationProperties
@Configuration
@AllArgsConstructor
@Slf4j
public class SmsAutoConfiguration {

    private SmsProperties smsProperties;
    private AsyncClient asyncClient;

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.ali", name = "access-key-id")
    public AliSmsClient aliSmsClient() {
        log.info("初始化阿里云短信");
        AliSmsClient aliSmsClient = new AliSmsClient(smsProperties, asyncClient);
        log.info("初始化阿里云短信成功");
        return aliSmsClient;
    }

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.tencent", name = "secret-id")
    public TencentSmsClient tencentSmsClient() {
        log.info("初始化腾讯云短信");
        TencentSmsClient tencentSmsClient = new TencentSmsClient(smsProperties, asyncClient);
        log.info("初始化腾讯云短信成功");
        return tencentSmsClient;
    }

}
