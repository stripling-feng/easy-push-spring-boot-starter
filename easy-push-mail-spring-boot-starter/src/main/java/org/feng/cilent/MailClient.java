package org.feng.cilent;

import org.feng.model.MailMessageBody;
import org.feng.properties.MailProperties;
import org.feng.support.EmailBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.io.File;
import java.io.IOException;

/**
 * @author feng
 *  2022/6/23 11:28
 *  Mailcilnet
 */
public class MailClient {

    @Autowired
    private AsyncMailClient asyncMailClient;

    public MailClient(MailProperties emailProperties) throws MessagingException, IOException {
        EmailBuild.build(emailProperties);
    }

    public void sendEmail(MailMessageBody messageBody) throws MessagingException, IOException {
        this.sendEmail(messageBody, (MultipartFile[]) null);
    }

    public void sendEmail(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    public void sendEmail(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    public void asyncSendEmail(MailMessageBody messageBody) throws MessagingException, IOException {
        asyncMailClient.asyncSendEmail(messageBody);
    }

    public void asyncSendEmail(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        asyncMailClient.asyncSendEmail(messageBody, files);
    }

    public void asyncSendEmail(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        asyncMailClient.asyncSendEmail(messageBody, files);
    }
}
