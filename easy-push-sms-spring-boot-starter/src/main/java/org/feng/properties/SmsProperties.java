package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 *  2022/6/24 10:21
 *  TODO
 */
@ConfigurationProperties(prefix = "easy-push.sms")
@Data
public class SmsProperties {
    private SmsAliProperties ali;
    private SmsTencentProperties tencent;
    private SmsAsyncProperties async;
}
