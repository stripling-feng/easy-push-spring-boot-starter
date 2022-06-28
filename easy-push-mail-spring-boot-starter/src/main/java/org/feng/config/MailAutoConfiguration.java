package org.feng.config;

import lombok.AllArgsConstructor;
import org.feng.cilent.MailClient;
import org.feng.properties.MailProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author feng
 *  2022/6/23 9:55
 *  MailAutoConfiguration
 */
@EnableConfigurationProperties
@Configuration
@ConditionalOnMissingBean(MailClient.class)
@AllArgsConstructor
public class MailAutoConfiguration {

    private MailProperties mailProperties;

    @Bean
    public MailClient emailClient() throws MessagingException, IOException {
        return new MailClient(mailProperties);
    }
}
