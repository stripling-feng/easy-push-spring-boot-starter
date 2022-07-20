package org.feng.config;

import org.feng.utils.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author feng
 */
public class ThreadPoolMdcConfiguration extends ThreadPoolTaskExecutor {
    @Autowired
    private ThreadMdcUtil threadMdcUtil;

    @Override
    public void execute(Runnable task) {
        super.execute(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super
                .submit(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super
                .submit(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }
}
