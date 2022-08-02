package org.feng.config;

import org.feng.enums.Rejected;
import org.feng.properties.SmsAsyncProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
        executor.setCorePoolSize(smsAsyncProperties.getCorePoolSize());
        executor.setMaxPoolSize(smsAsyncProperties.getMaxPoolSize());
        executor.setQueueCapacity(smsAsyncProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(smsAsyncProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(smsAsyncProperties.getThreadNamePrefix());
        if (smsAsyncProperties.getRejectedExecutionHandler() == Rejected.CallerRunsPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        } else if (smsAsyncProperties.getRejectedExecutionHandler() == Rejected.AbortPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        } else if (smsAsyncProperties.getRejectedExecutionHandler() == Rejected.DiscardOldestPolicy) {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        } else {
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
        executor.initialize();
    }
}
