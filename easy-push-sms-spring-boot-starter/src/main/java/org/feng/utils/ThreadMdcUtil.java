package org.feng.utils;

import org.feng.properties.SmsAsyncProperties;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author feng
 */
@Component
public class ThreadMdcUtil {
    @Autowired
    private SmsAsyncProperties smsAsyncProperties;

    public void setTraceIdIfAbsent() {
        if (MDC.get(smsAsyncProperties.getTraceIdName()) == null) {
            MDC.put(smsAsyncProperties.getTraceIdName(), UUID.randomUUID().toString().replace("-", ""));
        }
    }

    public <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            //设置traceId
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}