package com.github.houbb.json.exception;

import com.github.houbb.heaven.response.respcode.RespCode;

/**
 * <p> json 运行时异常 </p>
 *
 * <pre> Created: 2018-05-04 16:30  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since 0.0.3
 */
public class JsonRuntimeException extends RuntimeException {

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
