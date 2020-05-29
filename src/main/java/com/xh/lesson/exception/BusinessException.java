package com.xh.lesson.exception;

import com.xh.lesson.exception.code.ResponseCodeInterface;

/**
 * @ClassName: BusinessException
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/4 16:30
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/4 16:30
 * @Version: 0.0.1
 */
public class BusinessException extends RuntimeException{
    /**
     * 异常编号
     */
    private int messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private String detailMessage;

    public BusinessException(int messageCode,String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    public BusinessException(String detailMessage, Throwable cause) {
        super(detailMessage,cause);
    }
    /**
     * 构造函数
     * @param code 异常码
     */
    public BusinessException(ResponseCodeInterface code) {
        this(code.getCode(), code.getMsg());
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
