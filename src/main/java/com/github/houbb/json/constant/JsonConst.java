package com.github.houbb.json.constant;

/**
 * JSON 常量
 * @author binbin.hou
 * @since 0.0.1
 */
public final class JsonConst {

    private JsonConst(){}

    /**
     * 空信息
     * @since 0.0.1
     */
    public static final String NULL = "null";

    /**
     * 代理类名称
     * @since 0.1.4
     */
    public static final String PROXY_CLASS_NAME = "com.sun.proxy.$Proxy";

    /**
     * 是否为空 json
     * @param json json 信息
     * @return 是否
     * @since 0.0.1
     */
    public static boolean isNullJson(final String json) {
        return NULL.equals(json);
    }

}
