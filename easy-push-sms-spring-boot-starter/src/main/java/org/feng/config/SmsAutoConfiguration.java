package org.feng.config;

import lombok.extern.slf4j.Slf4j;
import org.feng.callback.AsyncCallback;
import org.feng.callback.impl.AsyncCallbackImpl;
import org.feng.cilent.AliSmsClient;
import org.feng.cilent.AsyncSmsClient;
import org.feng.cilent.TencentSmsClient;
import org.feng.enums.Rejected;
import org.feng.properties.SmsAsyncProperties;
import org.feng.properties.SmsProperties;
import org.feng.utils.ThreadMdcUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author feng
 * 2022/6/24 10:53
 *  TODO
 */
@EnableConfigurationProperties
@Configuration
@Slf4j
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.ali", name = "access-key-id")
    public AliSmsClient aliSmsClient(AsyncSmsClient asyncClient, SmsProperties smsProperties) {
        return new AliSmsClient(smsProperties, asyncClient);
    }

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.tencent", name = "secret-id")
    public TencentSmsClient tencentSmsClient(AsyncSmsClient asyncClient, SmsProperties smsProperties) {
        return new TencentSmsClient(smsProperties, asyncClient);
    }

    @Bean
    public AsyncSmsClient asyncSmsClient() {
        return new AsyncSmsClient(asyncCallback());
    }

    @Bean
    @ConditionalOnMissingBean(AsyncCallback.class)
    public AsyncCallback asyncCallback() {
        return new AsyncCallbackImpl();
    }

    @Bean({"smsAsyncExecutor"})
    public Executor asyncExecutor(SmsAsyncProperties smsAsyncProperties) {
        ThreadPoolTaskExecutor executor;
        if (smsAsyncProperties != null && smsAsyncProperties.getTraceIdName() != null) {
            executor = new ThreadPoolMdcConfiguration();
        } else {
            executor = new ThreadPoolTaskExecutor();
        }
        initExecutor(executor, smsAsyncProperties);
        return executor;
    }

    private void initExecutor(ThreadPoolTaskExecutor executor, SmsAsyncProperties smsAsyncProperties) {
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

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.async", name = "trace-id-name")
    public ThreadPoolMdcConfiguration threadPoolMdcConfiguration() {
        return new ThreadPoolMdcConfiguration();
    }

    @Bean
    @ConditionalOnProperty(prefix = "easy-push.sms.async", name = "trace-id-name")
    public ThreadMdcUtil threadMdcUtil() {
        return new ThreadMdcUtil();
    }
}
