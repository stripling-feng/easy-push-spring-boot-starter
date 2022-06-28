package org.feng.cilent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feng.model.MailMessageBody;
import org.feng.support.EmailBuild;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
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
@Component
@Slf4j
@AllArgsConstructor
public class AsyncMailClient {

    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody) throws MessagingException, IOException {
        this.asyncSendEmail(messageBody, (MultipartFile[]) null);
    }

    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody, MultipartFile[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }

    @Async("mailAsyncExecutor")
    protected void asyncSendEmail(MailMessageBody messageBody, File[] files) throws MessagingException, IOException {
        Transport.send(EmailBuild.buildMessage(messageBody, files));
    }


}
