package org.feng.support;

import lombok.extern.slf4j.Slf4j;
import org.feng.model.MailMessageBody;
import org.feng.properties.MailProperties;
import org.feng.thymeleaf.ResourceTemplateResolver;
import org.feng.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 构建Session会话
 *
 * @author feng
 * 2022/6/23 10:33
 */
@Slf4j
public class EmailBuild {

    private static final Map<String, Session> MAP = new ConcurrentHashMap<>();

    @Autowired
    private ResourceTemplateResolver resolver;


    /**
     * 初始化邮件session
     *
     * @param emailProperties mail properties
     */
    public static void build(MailProperties emailProperties) {
        buildSession(emailProperties);
    }


    /**
     * 构建公共消息体
     *
     * @param messageBody 消息体
     * @param msg         msg
     * @throws MessagingException MessagingException
     */
    private static void buildMessageProperties(MimeMessage msg, MailMessageBody messageBody) throws MessagingException {
        msg.setFrom(new InternetAddress(messageBody.getFrom()));
        msg.setRecipients(Message.RecipientType.TO, getAddress(messageBody.getTo()));
        if (!Objects.isNull(messageBody.getCc())) {
            msg.setRecipients(Message.RecipientType.CC, getAddress(messageBody.getCc()));
        }
        if (!Objects.isNull(messageBody.getBcc())) {
            msg.setRecipients(Message.RecipientType.BCC, getAddress(messageBody.getBcc()));
        }
        msg.setSubject(messageBody.getTitle());
        msg.setSentDate(new Date());
    }

    /**
     * 构建消息
     *
     * @param messageBody messageBody
     * @param files       net files
     * @return MimeMessage
     * @throws MessagingException MessagingException
     * @throws IOException        IOException
     */
    public static MimeMessage buildMessage(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        MimeMessage msg = new MimeMessage(MAP.get("session"));
        MimeBodyPart body = new MimeBodyPart();
        Multipart mp = new MimeMultipart();
        body.setContent(messageBody.getContent(), "text/html;charset=utf-8");
        mp.addBodyPart(body);
        if (!Objects.isNull(files) && files.length > 0) {
            for (MultipartFile file : files) {
                MimeBodyPart fileBodyPart = new MimeBodyPart();
                fileBodyPart.attachFile(FileUtils.multipartFileToFile(file));
                mp.addBodyPart(fileBodyPart);
            }
        }
        msg.setContent(mp);
        buildMessageProperties(msg, messageBody);
        return msg;
    }

    /**
     * 构建消息
     *
     * @param messageBody messageBody
     * @param files       files
     * @return MimeMessage
     * @throws MessagingException MessagingException
     * @throws IOException        IOException
     */
    public static MimeMessage buildMessage(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        MimeMessage msg = new MimeMessage(MAP.get("session"));
        MimeBodyPart body = new MimeBodyPart();
        Multipart mp = new MimeMultipart();
        body.setContent(messageBody.getContent(), "text/html;charset=utf-8");
        mp.addBodyPart(body);
        if (!Objects.isNull(files) && files.length > 0) {
            for (File file : files) {
                MimeBodyPart fileBodyPart = new MimeBodyPart();
                fileBodyPart.attachFile(file);
                mp.addBodyPart(fileBodyPart);
            }
        }
        msg.setContent(mp);
        buildMessageProperties(msg, messageBody);
        return msg;

    }

    /**
     * 构建邮件session
     *
     * @param emailProperties emailProperties
     */
    private static void buildSession(MailProperties emailProperties) {
        Session session = Session.getInstance(buildProperties(emailProperties), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (StringUtils.hasText(emailProperties.getUsername()) && StringUtils.hasText(emailProperties.getPassword())) {
                    return new PasswordAuthentication(emailProperties.getUsername(), emailProperties.getPassword());
                }
                return super.getPasswordAuthentication();
            }
        });
        session.setDebug(emailProperties.getDebug());
        MAP.put("session", session);
    }

    /**
     * 构建邮件配置
     *
     * @param emailProperties emailProperties
     * @return mail配置
     */
    private static Properties buildProperties(MailProperties emailProperties) {
        Properties props = new Properties();
        props.put("mail.smtp.host", emailProperties.getHost());
        props.put("mail.smtp.port", emailProperties.getPort());
        props.put("mail.smtp.auth", StringUtils.hasText(emailProperties.getUsername()) && StringUtils.hasText(emailProperties.getPassword()));
        props.put("mail.smtp.user", emailProperties.getUsername());
        props.put("mail.smtp.pass", emailProperties.getPassword());
        props.put("mail.smtp.ssl", emailProperties.getSsl());
        if (emailProperties.getSsl()) {
            props.put("mail.smtp.socketFactory.port", emailProperties.getPort());
            props.put("mail.smtp.socketFactory.class", emailProperties.getSslFactoryClass());
        }
        return props;
    }

    /**
     * 以逗号分割获取地址
     *
     * @param address address
     * @return Get addresses separated by commas
     * @throws AddressException AddressException
     */
    private static InternetAddress[] getAddress(String address) throws AddressException {
        String[] addressArray = address.split(",");
        InternetAddress[] internetAddresses = new InternetAddress[addressArray.length];
        for (int i = 0, j = addressArray.length; i < j; i++) {
            internetAddresses[i] = new InternetAddress(addressArray[i]);
        }
        return internetAddresses;
    }


}
