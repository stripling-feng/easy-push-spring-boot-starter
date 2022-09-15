package org.feng.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 *  2022/6/24 10:37
 *  TODO
 */
@ConfigurationProperties(prefix = "easy-push.sms.ali")
@Data
public class SmsAliProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
