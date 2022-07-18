package org.feng.model;

import lombok.Data;

/**
 * email 消息体
 *
 * @author feng
 */
@Data
public class MailMessageBody {
    /**
     * 发送目标
     */
    private String to;
    /**
     * 从哪发送
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
