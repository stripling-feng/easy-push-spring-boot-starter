package org.feng.cilent;

import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.feng.model.EasySmsResponse;
import org.feng.model.TencentSendSmsRequest;
import org.feng.properties.SmsProperties;
import org.feng.support.TencentSmsBuild;

import java.util.Objects;

/**
 * @author feng
 *  2022/6/29 14:34
 *  TODO
 */
@Slf4j
public class TencentSmsClient {
    private final SmsProperties smsProperties;

    private final AsyncSmsClient asyncClient;

    public TencentSmsClient(SmsProperties smsProperties, AsyncSmsClient asyncClient) {
        this.smsProperties = smsProperties;
        this.asyncClient = asyncClient;
    }

    /**
     * 同步消息发送
     *
     * @param tencentSendSmsRequest 信息发送主体
     * @return 消息返回体
     * @throws Exception 发送失败
     */
    public EasySmsResponse<com.tencentcloudapi.sms.v20210111.models.SendSmsResponse> sendSms(TencentSendSmsRequest tencentSendSmsRequest) throws Exception {
        com.tencentcloudapi.sms.v20210111.models.SendSmsResponse smsResponse = Objects.requireNonNull(TencentSmsBuild.buildTencentClient(smsProperties)).SendSms(buildSendSmsRequest(tencentSendSmsRequest));
        return EasySmsResponse.success(smsResponse);
    }

    /**
     * 使用异步进行消息发送
     *
     * @param tencentSendSmsRequest 信息发送主体
     * @throws Exception 发送失败
     */
    public void asyncSendSms(TencentSendSmsRequest tencentSendSmsRequest) throws Exception {
        asyncClient.tencentAsyncSendSms(Objects.requireNonNull(TencentSmsBuild.buildTencentClient(smsProperties)), buildSendSmsRequest(tencentSendSmsRequest));
    }

    private com.tencentcloudapi.sms.v20210111.models.SendSmsRequest buildSendSmsRequest(TencentSendSmsRequest tencentSendSmsRequest) {
        SendSmsRequest sendSmsRequest = new com.tencentcloudapi.sms.v20210111.models.SendSmsRequest();
        sendSmsRequest.setSmsSdkAppId(tencentSendSmsRequest.getSdkAppId());
        sendSmsRequest.setSignName(tencentSendSmsRequest.getSignName());
        sendSmsRequest.setTemplateId(tencentSendSmsRequest.getTemplateId());
        sendSmsRequest.setTemplateParamSet(tencentSendSmsRequest.getTemplateParams());
        sendSmsRequest.setPhoneNumberSet(buildPhoneNumber(tencentSendSmsRequest.getPhoneNumbers()));
        sendSmsRequest.setSessionContext(tencentSendSmsRequest.getSessionContext());
        sendSmsRequest.setExtendCode(tencentSendSmsRequest.getExtendCode());
        sendSmsRequest.setSenderId(tencentSendSmsRequest.getSenderId());
        return sendSmsRequest;
    }

    private String[] buildPhoneNumber(String phoneNumbers) {
        String[] phones = phoneNumbers.split(",");
        for (String phone : phones) {
            if (!phone.startsWith("+")) {
                phone = "+86" + phone;
            }
        }
        return phones;
    }
}
