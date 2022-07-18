package org.feng.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 模板配置
 *
 * @author feng
 */
@Data
@ConfigurationProperties(prefix = "easy-push.mail.template")
@Component
public class MailTemplateProperties {
    /**
     * 前缀
     */
    private String prefix = "/templates";
    /**
     * 后缀
     */
    private String suffix = ".html";
}
