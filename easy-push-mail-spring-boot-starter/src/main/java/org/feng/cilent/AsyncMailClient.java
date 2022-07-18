package org.feng.cilent;

import org.feng.model.MailMessageBody;
import org.feng.support.EmailBuild;
import org.feng.thymeleaf.ThymeleafBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
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
@Component
public class AsyncMailClient {
    @Autowired
    private ThymeleafBuild thymeleafBuild;

    /**
     * 异步发送邮件
     *
     * @param messageBody 消息体
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody) throws MessagingException, IOException {
        this.asyncSendEmail(messageBody, (MultipartFile[]) null);
    }

    /**
     * 异步发送邮件
     *
     * @param messageBody 消息体
     * @param files       网络文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 异步发送邮件
     *
     * @param messageBody 消息体
     * @param files       本地文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }


    /**
     * 异步发送邮件
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @param files        网络文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap, MultipartFile[] files) throws MessagingException, IOException {
        String content = thymeleafBuild.buildHtmlTemplate(templateName, dataMap);
        messageBody.setContent(content);
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 异步发送邮件
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @param files        本地文件
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap, File[] files) throws MessagingException, IOException {
        String content = thymeleafBuild.buildHtmlTemplate(templateName, dataMap);
        messageBody.setContent(content);
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    /**
     * 异步发送邮件
     *
     * @param messageBody  消息体
     * @param templateName 模板名称
     * @param dataMap      模板填充参数
     * @throws MessagingException 消息发送异常
     * @throws IOException        文件转换异常
     */
    @Async("mailAsyncExecutor")
    protected void asyncSendEmailHtmlTemplate(MailMessageBody messageBody, String templateName, HashMap<String, Object> dataMap) throws MessagingException, IOException {
        this.asyncSendEmailHtmlTemplate(messageBody, templateName, dataMap, (MultipartFile[]) null);
    }

}
