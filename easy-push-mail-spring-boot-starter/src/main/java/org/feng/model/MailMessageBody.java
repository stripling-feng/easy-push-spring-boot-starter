package org.feng.model;

import lombok.Data;

/**
 * @Author feng
 * @Date 2022/6/23 10:54
 * @Description 消息体
 */
@Data
public class MailMessageBody {
    /**
     * 发送目标
     * 多个目标使用逗号隔开
     */
    private String to;
    /**
     * 发送地址
     */
    private String from;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 抄送人
     */
    private String cc;
    /**
     * 密送人
     */
    private String bcc;
}
