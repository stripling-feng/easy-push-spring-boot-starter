package org.feng.model;

import lombok.Data;

/**
 * @author feng
 *  2022/6/24 11:08
 *  TODO
 */
@Data
public class EasySmsResponse<T> {
    /**
     * 通讯状态
     */
    private Integer Code;
    /**
     * 通讯描述
     */
    private String Message;
    /**
     * 平台返回信息
     */
    private T platformData;

    public EasySmsResponse(Integer code, String message, T platformData) {
        Code = code;
        Message = message;
        this.platformData = platformData;
    }

    public static <T> EasySmsResponse<T> success(T platformData){
        return new EasySmsResponse<>(200,"请求成功", platformData);
    }
}
