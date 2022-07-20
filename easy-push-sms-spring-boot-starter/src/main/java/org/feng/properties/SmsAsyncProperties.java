package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@ConfigurationProperties(prefix = "easy-push.sms.async")
@Data
@Component
public class SmsAsyncProperties {

    private String traceIdName;
}
