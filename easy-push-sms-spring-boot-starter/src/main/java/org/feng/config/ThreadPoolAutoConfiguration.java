package org.feng.config;

import org.feng.properties.SmsAsyncProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author feng
 * 2022/6/28 10:06
 *  TODO
 */
@Component
public class ThreadPoolAutoConfiguration {
    @Autowired
    private SmsAsyncProperties smsAsyncProperties;

    @Bean({"smsAsyncExecutor"})
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor;
        if (smsAsyncProperties != null && smsAsyncProperties.getTraceIdName() != null) {
            executor = new ThreadPoolMdcConfiguration();
        } else {
            executor = new ThreadPoolTaskExecutor();
        }
        initExecutor(executor);
        return executor;
    }
    private void initExecutor(ThreadPoolTaskExecutor executor) {
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("smsAsyncExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
    }
}
