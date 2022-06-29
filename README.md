# easy-push-spring-boot-starter
基于springboot starter 集成的一个 推送工具，目前支持，email 推送 ，阿里云短信

| 服务     | 分类   | client       |
| -------- | ------ | ------------ |
| 邮件服务 |        | MailClient   |
| 短信服务 | 阿里云 | AliSmsClient |
|          | 腾讯云 | xxxxxxxxx    |



## 配置详情

```yaml
easy-push:
  mail:
    enable: true
    host: smtp.163.com
    username: xxxxxxxx
    password: xxxxxxxx
    port: 25
    ssl: true
    debug: true
    ssl-factory-class: javax.net.ssl.SSLSocketFactory
  sms:
    ali:
      access-key-id: xxxxx
      access-key-secret: xxxxx
```

