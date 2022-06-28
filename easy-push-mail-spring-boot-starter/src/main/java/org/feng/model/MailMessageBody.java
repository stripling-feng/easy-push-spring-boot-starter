package org.feng.model;

import lombok.Data;

/**
 * @author feng
 *  2022/6/23 10:54
 *  MailMessageBody
 */
@Data
public class MailMessageBody {
    /**
     * send target
     * Multiple targets are separated by commas
     */
    private String to;
    /**
     * Send the address
     */
    private String from;
    /**
     * title
     */
    private String title;
    /**
     * content
     */
    private String content;
    /**
     * cc
     */
    private String cc;
    /**
     * bcc
     */
    private String bcc;
}
