package org.feng.config;

import lombok.AllArgsConstructor;
import org.feng.cilent.AliAsyncClient;
import org.feng.cilent.AliSmsClient;
import org.feng.properties.SmsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feng
 *  2022/6/24 10:53
 *  TODO
 */
@EnableConfigurationProperties
@Configuration
@AllArgsConstructor
public class SmsAutoConfiguration {

    private SmsProperties smsProperties;
    private AliAsyncClient asyncClient;

    @Bean
    public AliSmsClient aliSmsClient() {
        return new AliSmsClient(smsProperties, asyncClient);
    }

}
