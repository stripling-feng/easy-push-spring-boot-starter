package com.feng.example;

import com.alibaba.fastjson.JSON;
import org.feng.callback.AsyncCallback;
import org.feng.cilent.AliSmsClient;
import org.feng.cilent.AsyncMailClient;
import org.feng.cilent.MailClient;
import org.feng.enums.SmsType;
import org.feng.model.AliSendSmsRequest;
import org.feng.model.MailMessageBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

@SpringBootTest
@EnableAsync
class ExampleApplicationTests {

    @Autowired
    private MailClient mailClient;
    @Autowired
    private AsyncMailClient asyncMailClient;
    @Autowired
    private AliSmsClient aliSmsClient;
//------------------------------------------------邮件测试-------------------------------------------------------//

    /**
     * 发送普通邮件
     * 异步发送邮件
     */
    @Test
    void sendEmailAndAsyncSendEmail() {
        MailMessageBody body = new MailMessageBody();
        body.setFrom("15612509687@163.com");
        body.setTo("754443793@qq.com");
        body.setTitle("一份测试xxxxx邮件");
        body.setContent("ssssssssssssssss");
        try {
//            mailClient.sendEmail(body);
            asyncMailClient.asyncSendEmail(body);
            System.out.println("异步执行");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送携带文件邮件
     */
    @Test
    void sendEmailAndAsyncSendEmailWithFile() {
        MailMessageBody body = new MailMessageBody();
        body.setFrom("15612509687@163.com");
        body.setTo("754443793@qq.com");
        body.setTitle("一份测sss试邮ssd件");
        body.setContent("ssssssssssssssss");
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
        try {
            mailClient.sendEmail(body, new File[]{file});
//            asyncMailClient.asyncSendEmail(body, new File[]{file});
//            System.out.println("异步执行");
//            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送thymeaf模板 邮件
     */
    @Test
    void sendEmailAndAsyncSendEmailWithThymeleafTemplate() {
        MailMessageBody body = new MailMessageBody();
        body.setFrom("15612509687@163.com");
        body.setTo("754443793@qq.com");
        body.setTitle("一份s测试邮件");
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
        HashMap<String, Object> map = new HashMap<>();
        map.put("showText", "欢迎使用easy-push");
        try {
//            mailClient.sendEmailHtmlTemplate(body, "test", map);
//            mailClient.sendEmailHtmlTemplate(body, "test", map, new File[]{file});
            asyncMailClient.asyncSendEmailHtmlTemplate(body, "test", map);
            asyncMailClient.asyncSendEmailHtmlTemplate(body, "test", map, new File[]{file});
            System.out.println("异步执行");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------短信测试-------------------------------------------------------//

    @Test
    void sendSms() {
        AliSendSmsRequest request = new AliSendSmsRequest();
        request.setTemplateCode("xxxxx");
        request.setSignName("xxxx");
        request.setPhoneNumbers("15612544688");
        request.setOutId("123456798");
        HashMap<String, String> map = new HashMap<>();
        map.put("code", "123456");
        request.setTemplateParam(map);
        try {
//            EasySmsResponse<SendSmsResponseBody> sms = aliSmsClient.sendSms(request);
            aliSmsClient.asyncSendSms(request);
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Service
    class SmsCallBack implements AsyncCallback {
        @Override
        public void callback(Object body, SmsType smsType) {
            System.out.println("异步短信回调");
            System.out.println(JSON.toJSONString(body));
            System.out.println(smsType);
        }
    }
}
