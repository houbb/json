/*
 * Copyright (c)  2019. houbinbin Inc.
 * hades All rights reserved.
 */

package com.github.houbb.json.test.model;

import com.github.houbb.json.test.enums.MethodEnum;

import java.util.Arrays;

/**
 * 枚举
 * 对象验证
 *
 * @author binbin.hou
 * @since 0.1.0
 */
public class PersistAofBean {

    /**
     * 指令发生的时间
     */
    private long nanoTime;

    /**
     * 指令名称
     */
    private MethodEnum method;

    /**
     * 指令参数
     */
    private Object[] params;

    public long getNanoTime() {
        return nanoTime;
    }

    public void setNanoTime(long nanoTime) {
        this.nanoTime = nanoTime;
    }

    public MethodEnum getMethod() {
        return method;
    }

    public void setMethod(MethodEnum method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PersistAofBean{" +
                "nanoTime=" + nanoTime +
                ", method=" + method +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
