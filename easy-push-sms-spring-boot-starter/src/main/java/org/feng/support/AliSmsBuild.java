package org.feng.support;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.feng.properties.SmsAliProperties;
import org.feng.properties.SmsProperties;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author feng
 * @Date 2022/6/24 10:08
 * @Description ali 客户端构造器
 */
@Slf4j
public class AliSmsBuild {
    private static final Map<String, Client> MAP = new ConcurrentHashMap<>();

    private static Config buildAliConfig(SmsAliProperties aliProperties) {
        Config config = new Config()
                .setAccessKeyId(aliProperties.getAccessKeyId())
                .setAccessKeySecret(aliProperties.getAccessKeySecret());
        config.endpoint = "dysmsapi.aliyuncs.com";
        return config;
    }

    public static Client buildAliClient(SmsProperties smsProperties) {
        if (Objects.isNull(smsProperties) && Objects.isNull(smsProperties.getAli())) {
            log.info("ali_client未进行配置，无法发送");
            return null;
        }
        Client aliClient = MAP.get("ali_client");
        if (!Objects.isNull(aliClient)) {
            return aliClient;
        }
        Config config = buildAliConfig(smsProperties.getAli());
        try {
            aliClient = new Client(config);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ali_client构建失败");
        }
        MAP.put("ali_client", aliClient);
        log.info("--------------------初始化ALIClient成功----------------------------");
        return aliClient;
    }
}
