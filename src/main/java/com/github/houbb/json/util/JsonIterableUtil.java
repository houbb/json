package com.github.houbb.json.util;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.json.constant.JsonIterableConst;

import java.util.List;

/**
 * json 遍历工具类
 * @author binbin.hou
 * @since 0.0.4
 * @see JsonIterableConst 常量
 */
public final class JsonIterableUtil {

    private JsonIterableUtil(){}

    /**
     * 构建数组 json
     * @param stringList 元素对应的 json 信息
     * @return json 结果
     * @since 0.0.4
     */
    public static String buildArrayJson(List<String> stringList) {
        return JsonIterableConst.START + StringUtil.join(stringList, PunctuationConst.COMMA) +
                JsonIterableConst.END;
    }

    /**
     * 是否为空格，或者逗号
     * @param c 字符
     * @return 是否
     * @since 0.0.4
     */
    public static boolean isBlankOrComma(final char c) {
        return ' ' == c || ',' == c;
    }

    public static void main(String[] args) {
        String string = "\\\"123";

        System.out.println("\\\"123".equals(string));
        System.out.println(string);
    }

}
