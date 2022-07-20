package org.feng.callback.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feng.callback.AsyncCallback;
import org.feng.enums.SmsType;

/**
 * @author feng
 */
@Slf4j
public class AsyncCallbackImpl implements AsyncCallback {
    @Override
    public void callback(Object body, SmsType smsType) {
        log.info("短信返回内容：{}", JSON.toJSONString(body));
        log.info("短信类型：{}", smsType);
    }
}
