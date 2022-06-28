package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 *  2022/6/24 9:28
 *  Mail配置字段
 */
@Data
@ConfigurationProperties(prefix = "easy-push.mail")
@Component
public class MailProperties {
    /**
     * enable
     */
    private Boolean enable;
    /**
     * host
     */
    private String host;
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;
    /**
     * port
     */
    private String port;
    /**
     * debug
     */
    private Boolean debug;
    /**
     * ssl
     */
    private Boolean ssl;
    /**
     * sslFactoryClass
     */
    private String sslFactoryClass;
}
