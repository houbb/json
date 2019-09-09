package com.github.houbb.json.exception;

import com.github.houbb.heaven.response.respcode.RespCode;

/**
 * json 响应编码
 * @author binbin.hou
 * @since 0.0.4
 */
public enum  JsonRespCode implements RespCode {

    /**
     * 反序列化非字符
     */
    DES_INVALID_SYMBOL("10001", "非法字符"),
    DES_NEED_DOUBLE_QUOTES("10002", "反序列化需要\""),
    DES_NEED_COMMA("10003", "反序列化需要,"),
    DES_NEED_ITERABLE_START("10004", "反序列化需要["),
    DES_NEED_ITERABLE_END("10005", "反序列化需要]"),
    DES_NEED_COMMA_BLANK("10006", "反序列化需要,或者空格"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 信息
     */
    private final String msg;

    JsonRespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
