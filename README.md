# easy-push-spring-boot-starter

## 简介



基于springboot starter 集成的一个 推送工具。**easy-push-spring-boot-starter** 是聚合服务。



## 服务功能



**[easy-push-mail-spring-boot-starter](https://github.com/stripling-feng/easy-push-spring-boot-starter/tree/main/easy-push-mail-spring-boot-starter)** **邮件服务**

- **发送普通邮件**

- **发送携带文件邮件**

- **支持thymeleaf模板，发送邮件**

- **支持异步发送邮件**

  

**[easy-push-sms-spring-boot-starter](https://github.com/stripling-feng/easy-push-spring-boot-starter/tree/main/easy-push-sms-spring-boot-starter)** **短信服务**

- **支持阿里云短信**

- **支持腾讯云短信**

- **支持异步发送短信，并返回异步发送结果**

  

## 服务Client

| 服务     | 分类   | client           |
| -------- | ------ | ---------------- |
| 邮件服务 |        | MailClient       |
| 短信服务 | 阿里云 | AliSmsClient     |
|          | 腾讯云 | TencentSmsClient |



## 使用

**使用聚合包**

```xml
<dependency>
    <groupId>io.github.stripling-feng</groupId>
    <artifactId>easy-push-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

**或者根据需要选择**

**邮件：**

```xml
<dependency>
    <groupId>io.github.stripling-feng</groupId>
    <artifactId>easy-push-mail-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

**短信：**

```xml
<dependency>
    <groupId>io.github.stripling-feng</groupId>
    <artifactId>easy-push-sms-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

注入[服务详情](#service)中的client ，使用API即可

## **注：** 

## **使用异步需开启springAsync注解   @EnableAsync**

