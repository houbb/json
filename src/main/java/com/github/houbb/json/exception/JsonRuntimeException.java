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
}
