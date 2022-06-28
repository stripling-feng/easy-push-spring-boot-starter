package org.feng.cilent;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author feng
 * @Date 2022/6/27 16:11
 * @Description ali 异步发送
 */
@Component
@Slf4j
@AllArgsConstructor
public class AliAsyncClient {

    private org.feng.callback.asyncCallback asyncCallback;

    @Async("smsAsyncExecutor")
    protected void asyncSendSms(Client client, SendSmsRequest sendSmsRequest) {
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("AliClient异步发送消息失败");
        }
        asyncCallback.callback(sendSmsResponse.getBody());
    }
}
