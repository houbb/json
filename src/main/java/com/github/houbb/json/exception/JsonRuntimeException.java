<<<<<<< HEAD
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.exception;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:30  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonRuntimeException extends RuntimeException {

    public JsonRuntimeException() {
    }

    public JsonRuntimeException(String message) {
        super(message);
    }

    public JsonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonRuntimeException(Throwable cause) {
        super(cause);
    }

    public JsonRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
=======
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

>>>>>>> 2074a83fe5285befb123d0b44dd18df82d574eab
}
