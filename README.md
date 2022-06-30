# easy-push-spring-boot-starter
基于springboot starter 集成的一个 推送工具，目前支持，email 推送 ，阿里云短信

## 服务详情

| 服务     | 分类   | client           |
| -------- | ------ | ---------------- |
| 邮件服务 |        | MailClient       |
| 短信服务 | 阿里云 | AliSmsClient     |
|          | 腾讯云 | TencentSmsClient |



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

**注：** **使用aysncXXXX API 需开启springAsync注解   例：@EnableAsync**

###  SMS使用异步发送

实现 AsyncCallback 接口并添加至spring容器中，可以实现异步发送成功之后的回调

例：

```java
public interface AsyncCallback {
    /**
     * async 回调 需要实现这个接口 进行实现
     *
     * @param body 返回实体
     */
    public void callback(Object body, SmsType smsType);
}

```

```java
@Component
public class AsyncCallback implements org.feng.callback.AsyncCallback {

    @Override
    public void callback(Object body, SmsType smsType) {
        System.out.println("实现回调");
        System.out.println(JSON.toJSONString(body));
        System.out.println(smsType);
    }
}
```

