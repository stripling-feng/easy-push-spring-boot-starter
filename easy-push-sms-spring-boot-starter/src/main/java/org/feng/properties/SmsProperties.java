package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author feng
 * @Date 2022/6/24 10:21
 * @Description TODO
 */
@ConfigurationProperties(prefix = "easy-push.sms")
@Data
@Component
public class SmsProperties {
    private SmsAliProperties ali;
}
