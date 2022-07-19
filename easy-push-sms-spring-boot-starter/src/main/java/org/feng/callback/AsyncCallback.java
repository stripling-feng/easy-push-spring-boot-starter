package org.feng.callback;

import org.feng.enums.SmsType;

/**
 * @author feng
 * 2022/6/27 17:03
 * async回调
 */
public interface AsyncCallback {
    /**
     * async 回调 需要实现这个接口 进行实现
     *
     * @param body    返回实体
     * @param smsType 短信类型
     */
    default void callback(Object body, SmsType smsType) {

    }
}
