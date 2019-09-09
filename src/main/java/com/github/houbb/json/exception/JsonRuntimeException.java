package com.github.houbb.json.exception;

import com.github.houbb.heaven.response.respcode.RespCode;

/**
 * json 运行时异常
 * @author binbin.hou
 * @since 0.0.4
 */
public class JsonRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -4084237447709915881L;

    /**
     * 响应编码
     */
    private final RespCode respCode;

    public JsonRuntimeException(RespCode respCode) {
        this.respCode = respCode;
    }

    public JsonRuntimeException(String message, RespCode respCode) {
        super(message);
        this.respCode = respCode;
    }

    public JsonRuntimeException(String message, Throwable cause, RespCode respCode) {
        super(message, cause);
        this.respCode = respCode;
    }

    public JsonRuntimeException(Throwable cause, RespCode respCode) {
        super(cause);
        this.respCode = respCode;
    }

    public JsonRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RespCode respCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.respCode = respCode;
    }

}
