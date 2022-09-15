# easy-push-mail-spring-boot-starter

## 简介

基于springboot starter 集成的一个email推送工具

  **支持**
- **发送普通邮件**

- **发送携带文件邮件**

- **支持thymeleaf模板，发送邮件**

- **支持异步发送邮件**


## 快速入门

```xml
<dependency>
    <groupId>io.github.stripling-feng</groupId>
    <artifactId>easy-push-mail-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```

```java
//注入
private MailClient mailClient;

//使用
MailMessageBody body = new MailMessageBody();
body.setFrom("15612509687@163.com");
body.setTo("754443793@qq.com");
body.setTitle("测试模板");
mailClient.sendEmail(body);
```



## 全部配置详情

```yaml
easy-push:
  mail:
    host: smtp.163.com
    username: xxxx@163.com
    password: xxxx
    port: 25
    ssl: true
    debug: true
    ssl-factory-class: javax.net.ssl.SSLSocketFactory
    pool:
      core-pool: 10
      max-pool: 100
      queue-capacity: 20
      keep-alive: 60
      #线程名称
      thread-name-prefix: mypool
    template:
      # thymeleaf 模板路径 默认在/resources/templates 下
      prefix: /templates
      # 模板后缀 默认.html
      suffix: .html
```

## 服务Client

| 服务     | client          | 描述               |
| -------- | --------------- | ------------------ |
| 邮件服务 | MailClient      | 发送邮件client     |
|          | AsyncMailClient | 异步发送邮件client |

## **注：** 

## **使用异步需开启springAsync注解   @EnableAsync**
