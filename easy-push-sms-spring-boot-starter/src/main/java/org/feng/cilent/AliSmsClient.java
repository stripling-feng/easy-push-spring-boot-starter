package org.feng.cilent;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.feng.model.AliSendSmsRequest;
import org.feng.model.EasySmsResponse;
import org.feng.properties.SmsProperties;
import org.feng.support.AliSmsBuild;

import java.util.Objects;

/**
 * @author feng
 *  2022/6/24 10:52
 *  阿里短信客户端
 */
@Slf4j
public class AliSmsClient {

    private final SmsProperties smsProperties;
    private final AliAsyncClient asyncClient;

    public AliSmsClient(SmsProperties smsProperties, AliAsyncClient asyncClient) {
        this.smsProperties = smsProperties;
        this.asyncClient = asyncClient;
    }

    /**
     * 同步消息发送
     *
     * @param aliSendSmsRequest 信息发送主体
     * @return
     * @throws Exception
     */
    public EasySmsResponse<SendSmsResponseBody> sendSms(AliSendSmsRequest aliSendSmsRequest) throws Exception {
        SendSmsResponse sendSmsResponse = Objects.requireNonNull(AliSmsBuild.buildAliClient(smsProperties)).sendSms(buildSendSmsRequest(aliSendSmsRequest));
        return EasySmsResponse.success(sendSmsResponse.getBody());
    }

    /**
     * 使用异步进行消息发送
     *
     * @param aliSendSmsRequest 信息发送主体
     * @throws Exception
     */
    public void asyncSendSms(AliSendSmsRequest aliSendSmsRequest) throws Exception {
        asyncClient.asyncSendSms(Objects.requireNonNull(AliSmsBuild.buildAliClient(smsProperties)), buildSendSmsRequest(aliSendSmsRequest));
    }

    private SendSmsRequest buildSendSmsRequest(AliSendSmsRequest aliSendSmsRequest) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(aliSendSmsRequest.getPhoneNumbers())
                .setSignName(aliSendSmsRequest.getSignName())
                .setTemplateParam(JSON.toJSONString(aliSendSmsRequest.getTemplateParam()))
                .setOutId(aliSendSmsRequest.getOutId())
                .setTemplateCode(aliSendSmsRequest.getTemplateCode());
        return sendSmsRequest;
    }
}
