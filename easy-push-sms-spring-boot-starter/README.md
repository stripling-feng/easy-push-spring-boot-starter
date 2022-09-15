# easy-push-mail-spring-boot-starter

## 简介

基于springboot starter 集成的一个短信推送工具，目前支持阿里云短信，腾讯云短信。

  **支持**
- **支持阿里云短信**

- **支持腾讯云短信**

- **支持异步发送短信，并返回异步发送结果**
## 快速入门



```xml
<dependency>
    <groupId>io.github.stripling-feng</groupId>
    <artifactId>easy-push-sms-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```
## 配置详情

```yaml
easy-push:
  sms:
    #客户端配置
    ali:
      access-key-id: xxxx
      access-key-secret: xxxxx
    #异步配置
    async:
      trace-id-name: traceId  #lockback 多线程日志追踪id名称
      #线程池配置
      core-pool-size: 10  #默认10
      max-pool-size: 100  #默认100
      thread-name-prefix: threadPrefix #默认smsAsyncExecutor-
      keep-alive-seconds: 60  #默认60
      queue-capacity: 20  #默认20
```


```java
//阿里云短信 同步发送示例

@Autowired
private AliSmsClient aliSmsClient;

//使用
AliSendSmsRequest request = new AliSendSmsRequest();
request.setPhoneNumbers("111111,2222220");
request.setSignName("11111");
request.setTemplateCode("11111");
HashMap<String, String> params = new HashMap<>();
request.setTemplateParam(params);
request.setOutId("123456");
try {
  aliSmsClient.sendSms(request);
} catch (Exception e) {
  e.printStackTrace();
}
```





## 服务Client

| 服务     | client           | 描述            |
| -------- | ---------------- |---------------|
| 短信服务 | AliSmsClient     | 阿里云发送邮件client |
|          | TencentSmsClient | 腾讯云发送邮件client |

###  SMS使用异步发送

实现 AsyncCallback 接口并添加至spring容器中，可以实现异步发送成功之后的回调

例：
```java
//阿里云短信 异步发送示例

@Autowired
private AliSmsClient aliSmsClient;

//使用
AliSendSmsRequest request = new AliSendSmsRequest();
request.setPhoneNumbers("111111,2222220");
request.setSignName("11111");
request.setTemplateCode("11111");
HashMap<String, String> params = new HashMap<>();
request.setTemplateParam(params);
request.setOutId("123456");
try {
  aliSmsClient.asyncSendSms(request);
} catch (Exception e) {
  e.printStackTrace();
}
```
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

## **注：** 

## **使用异步需开启springAsync注解   @EnableAsync**
