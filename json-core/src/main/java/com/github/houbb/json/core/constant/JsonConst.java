package com.github.houbb.json.core.constant;

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
     * 是否为空 json
     * @param json json 信息
     * @return 是否
     * @since 0.0.1
     */
    public static boolean isNullJson(final String json) {
        return NULL.equals(json);
    }

}
