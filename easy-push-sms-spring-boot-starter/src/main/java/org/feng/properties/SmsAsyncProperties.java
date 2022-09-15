package org.feng.properties;

import lombok.Data;
import org.feng.enums.Rejected;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@ConfigurationProperties(prefix = "easy-push.sms.async")
@Data
public class SmsAsyncProperties {
    /**
     * 多线程logback 日志追踪字段
     */
    private String traceIdName;

    private Integer corePoolSize = 10;
    private Integer maxPoolSize = 100;
    private Integer queueCapacity = 20;
    private Integer keepAliveSeconds = 60;
    private String threadNamePrefix = "smsAsyncExecutor-";
    private Rejected rejectedExecutionHandler = Rejected.CallerRunsPolicy;

}
