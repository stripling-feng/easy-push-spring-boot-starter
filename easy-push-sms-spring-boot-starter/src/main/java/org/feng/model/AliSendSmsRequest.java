package org.feng.model;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author feng
 * @Date 2022/6/24 10:57
 * @Description TODO
 */
@Data
public class AliSendSmsRequest {
    /**
     * 接收短信的手机号码。
     * <p>
     * 手机号码格式：
     * <p>
     * 国内短信：+/+86/0086/86或无任何前缀的11位手机号码，例如1590000****。
     * 国际/港澳台消息：国际区号+号码，例如852000012****。
     * 支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
     */
    private String phoneNumbers;

    /**
     * 短信签名名称。
     */
    private String signName;

    /**
     * 短信模板CODE。
     */
    private String templateCode;
    /**
     * 短信模板变量对应的实际值
     */
    private HashMap<String, String> templateParam;
    /**
     * 外部流水扩展字段。
     */
    private String outId;
}
