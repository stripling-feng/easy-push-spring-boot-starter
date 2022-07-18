package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 * 2022/6/24 9:28
 * Mail配置字段
 */
@Data
@ConfigurationProperties(prefix = "easy-push.mail")
@Component
public class MailProperties {
    /**
     * 邮箱地址
     * 例:smtp.163.com
     */
    private String host;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码(授权码)
     */
    private String password;
    /**
     * 端口
     */
    private String port;
    /**
     * 是否开启debug
     */
    private Boolean debug = false;
    /**
     * 是否开启ssl
     */
    private Boolean ssl = true;
    /**
     * sslFactoryClass
     */
    private String sslFactoryClass = "javax.net.ssl.SSLSocketFactory";
}
