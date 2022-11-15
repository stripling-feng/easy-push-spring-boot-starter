package org.feng.config;

import org.feng.utils.ThreadMdcUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    public void execute(@NotNull Runnable task) {
        super.execute(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @NotNull
    @Override
    public Future<?> submit(@NotNull Runnable task) {
        return super
                .submit(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }

    @NotNull
    @Override
    public <T> Future<T> submit(@NotNull Callable<T> task) {
        return super
                .submit(threadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
    }
}
