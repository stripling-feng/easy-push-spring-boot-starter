package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author feng
 */
@Data
@ConfigurationProperties(prefix = "easy-push.mail.pool")
@Component
public class MailPoolProperties {
    private Integer corePool = 10;
    private Integer maxPool = 100;
    private Integer queueCapacity = 20;
    private Integer keepAlive = 60;
    private String threadNamePrefix = "mailAsyncExecutor-";
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
}
