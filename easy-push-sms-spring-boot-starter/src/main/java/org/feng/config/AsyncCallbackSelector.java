package org.feng.config;

import org.feng.callback.AsyncCallback;
import org.feng.callback.impl.AsyncCallbackImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 异步短信结果接口选择器
 *
 * @author feng
 */
@Configuration
public class AsyncCallbackSelector {

    @Bean
    @ConditionalOnMissingBean(AsyncCallback.class)
    public AsyncCallback asyncCallback() {
        return new AsyncCallbackImpl();
    }
}
