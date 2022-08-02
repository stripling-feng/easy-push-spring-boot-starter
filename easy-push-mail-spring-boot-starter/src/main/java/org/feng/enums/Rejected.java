package org.feng.enums;

/**
 * @author feng
 */
public enum Rejected {

    /**
     * 直接抛出RejectedExecutionException异常阻止系统正常运行。
     */
    AbortPolicy(),
    /**
     * 丢弃线称队列的旧的任务，将新的任务添加
     */
    DiscardOldestPolicy(),
    /**
     * 丢弃任务，但是不抛出异常
     */
    DiscardPolicy(),
    /**
     * ⽤调⽤者所在的线程来处理任务
     */
    CallerRunsPolicy();
}
