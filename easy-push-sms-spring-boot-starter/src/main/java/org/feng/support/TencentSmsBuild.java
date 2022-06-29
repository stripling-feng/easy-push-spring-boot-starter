package org.feng.support;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.feng.properties.SmsProperties;
import org.feng.properties.SmsTencentProperties;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author feng
 * 2022/6/24 10:08
 * ali 客户端构造器
 */
@Slf4j
public class TencentSmsBuild {

    private static final Map<String, SmsClient> MAP = new ConcurrentHashMap<>();

    private static SmsClient buildTencentConfig(SmsTencentProperties tencentProperties) {
        Credential cred = new Credential(tencentProperties.getSecretId(), tencentProperties.getSecretKey());
        ClientProfile clientProfile = new ClientProfile();
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod("POST");
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint("sms.tencentcloudapi.com");
        clientProfile.setHttpProfile(httpProfile);
        return new SmsClient(cred, "ap-guangzhou", clientProfile);
    }

    public static SmsClient buildTencentClient(SmsProperties smsProperties) {
        if (Objects.isNull(smsProperties) && Objects.isNull(smsProperties.getTencent())) {
            log.info("tencent_client未进行配置，无法发送");
            return null;
        }
        SmsClient tencentClient = MAP.get("tencent_client");
        if (!Objects.isNull(tencentClient)) {
            return tencentClient;
        }
        SmsClient smsClient = buildTencentConfig(smsProperties.getTencent());
        MAP.put("tencent_client", smsClient);
        log.info("--------------------初始化tencent_client成功----------------------------");
        return smsClient;
    }
}
