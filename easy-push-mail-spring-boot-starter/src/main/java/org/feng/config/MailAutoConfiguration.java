package org.feng.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feng.cilent.MailClient;
import org.feng.properties.MailProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author feng
 * 2022/6/23 9:55
 * MailAutoConfiguration
 */
@EnableConfigurationProperties
@Configuration
@AllArgsConstructor
@Slf4j
@ConditionalOnMissingBean(MailClient.class)
@ConditionalOnProperty(prefix = "easy-push.mail", value = "enable", havingValue = "true")
public class MailAutoConfiguration {

    private MailProperties mailProperties;

    @Bean
    public MailClient emailClient() throws MessagingException, IOException {
        log.info("初始化mail");
        MailClient mailClient = new MailClient(mailProperties);
        log.info("初始化mail成功");
        return mailClient;
    }
}
