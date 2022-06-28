package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author feng
 * @Date 2022/6/24 9:28
 * @Description Mail配置字段
 */
@Data
@ConfigurationProperties(prefix = "easy-push.mail")
@Component
public class MailProperties {
    /**
     * 是否开启
     */
    private Boolean enable;
    /**
     * 服务端口
     */
    private String host;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 授权码
     */
    private String password;
    /**
     * 端口
     */
    private String port;
    /**
     * 是否开启debug
     */
    private Boolean debug;
    /**
     * ssl
     */
    private Boolean ssl;
    /**
     * ssl类
     */
    private String sslFactoryClass;
}
