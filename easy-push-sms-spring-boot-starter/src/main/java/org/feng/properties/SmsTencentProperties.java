package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 * 2022/6/24 10:37
 *  TODO
 */
@ConfigurationProperties(prefix = "easy-push.sms.tencent")
@Data
@Component
public class SmsTencentProperties {
    private String secretId;
    private String secretKey;
}
