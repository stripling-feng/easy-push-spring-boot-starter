package org.feng.cilent;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.feng.callback.AsyncCallback;
import org.feng.enums.SmsType;
import org.springframework.scheduling.annotation.Async;

/**
 * @author feng
 * 2022/6/27 16:11
 * ali 异步发送
 */
@Slf4j
public class AsyncSmsClient {

    private final AsyncCallback asyncCallback;

    public AsyncSmsClient(AsyncCallback asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Async("smsAsyncExecutor")
    protected void aliAsyncSendSms(Client client, SendSmsRequest sendSmsRequest) {
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = client.sendSms(sendSmsRequest);
            asyncCallback.callback(sendSmsResponse.getBody(), SmsType.ALI);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("AliClient异步发送消息失败");
        }
    }

    protected void tencentAsyncSendSms(SmsClient client, com.tencentcloudapi.sms.v20210111.models.SendSmsRequest sendSmsRequest) {

        com.tencentcloudapi.sms.v20210111.models.SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = client.SendSms(sendSmsRequest);
            asyncCallback.callback(sendSmsResponse, SmsType.TENCENT);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("TencentClient异步发送消息失败");
        }
    }
}
