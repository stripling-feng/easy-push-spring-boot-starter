package org.feng.cilent;

import org.feng.model.MailMessageBody;
import org.feng.properties.MailProperties;
import org.feng.support.EmailBuild;
import org.feng.thymeleaf.ThymeleafBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author feng
 * 2022/6/23 11:28
 * Mailcilnet
 */
public class MailClient {
    @Autowired
    private ThymeleafBuild thymeleafBuild;

    /**
     * 初始化邮件各种配置
     *
     * @param emailProperties 邮件配置
     */
    public MailClient(MailProperties emailProperties) {
        EmailBuild.build(emailProperties);
    }

    /**
     * 发送邮件
     *
     * @param messageBody 发送消息体
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmail(MailMessageBody messageBody) throws MessagingException, IOException {
        this.sendEmail(messageBody, (MultipartFile[]) null);
    }

    /**
     * 发送邮件
     *
     * @param messageBody 发送消息体
     * @param files       网络文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmail(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 发送邮件
     *
     * @param messageBody 发送消息体
     * @param files       本地文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmail(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 发送thymeleaf 模板消息
     * 模板默认放在 /resources/templates 下
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @param files        本地文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap, File[] files) throws MessagingException, IOException {
        String content = thymeleafBuild.buildHtmlTemplate(templateName, dataMap);
        messageBody.setContent(content);
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 发送thymeleaf 模板消息
     * 模板默认放在 /resources/templates 下
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @param files        网络文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap, MultipartFile[] files) throws MessagingException, IOException {
        String content = thymeleafBuild.buildHtmlTemplate(templateName, dataMap);
        messageBody.setContent(content);
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 发送thymeleaf 模板消息
     * 模板默认放在 /resources/templates 下
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    public void sendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap) throws MessagingException, IOException {
        this.sendEmailHtmlTemplate(messageBody, templateName, dataMap, (File[]) null);
    }


}
