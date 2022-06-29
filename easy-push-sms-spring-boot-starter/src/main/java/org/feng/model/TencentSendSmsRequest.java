package org.feng.model;

import lombok.Data;

/**
 * @author feng
 * 2022/6/24 10:57
 *  TODO
 */
@Data
public class TencentSendSmsRequest {

    /**
     * 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666
     */
    private String sdkAppId;
    /**
     * 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
     */
    private String signName;
    /**
     * 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
     */
    private String templateId;
    /**
     * 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空
     */
    private String[] templateParams;
    /**
     * 手机号码以,分割  ，国内 不需要加+86   国外必须加地区码
     */
    private String phoneNumbers;
    /**
     * 可以携带用户侧 ID 等上下文信息，server 会原样返回
     */
    private String sessionContext;
    /**
     * 短信码号扩展号（无需要可忽略）: 默认未开通，如需开通请联系 [腾讯云短信小助手]
     */
    private String extendCode;
    /**
     * 国际/港澳台短信 SenderId（无需要可忽略）: 国内短信填空，默认未开通，如需开通请联系 [腾讯云短信小助手]
     */
    private String senderId;
}
