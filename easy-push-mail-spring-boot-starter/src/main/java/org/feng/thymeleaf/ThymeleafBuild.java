package org.feng.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;

/**
 * 模板构建
 *
 * @author feng
 */
@Component
public class ThymeleafBuild {
    @Autowired
    private ResourceTemplateResolver resolver;
    private static final String TEMPLATE_PREFIX_TAG = "/";
    private static final String TEMPLATE_SUFFIX_TAG = ".";

    /**
     * thymeleaf 解析 用户配置模板
     *
     * @param templateName 模板名称
     * @param map          模板参数
     * @return 模板字符串
     */
    public String buildHtmlTemplate(String templateName, HashMap<String, Object> map) {
        TemplateEngine templateEngine = new TemplateEngine();
        Context context = new Context();
        context.setVariables(map);
        String prefix = resolver.getPrefix();
        String suffix = resolver.getSuffix();
        if (!prefix.startsWith(TEMPLATE_PREFIX_TAG)) {
            resolver.setPrefix(TEMPLATE_PREFIX_TAG + prefix);
        }
        if (!suffix.startsWith(TEMPLATE_SUFFIX_TAG)) {
            resolver.setSuffix(TEMPLATE_SUFFIX_TAG + suffix);
        }
        if (!templateName.startsWith(TEMPLATE_PREFIX_TAG)) {
            templateName = TEMPLATE_PREFIX_TAG + templateName;
        }
        templateEngine.setTemplateResolver(resolver);
        return templateEngine.process(templateName, context);
    }
}
