package com.github.houbb.json.util;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.scanner.impl.JsonArrayObjectScanner;
import com.github.houbb.json.support.scanner.impl.JsonIterableScanner;

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

    /**
     * 获取可遍历的 json 对应的分割信息
     * @param json json 信息
     * @param tClass 类信息
     * @return 结果列表
     * @since 0.1.4
     */
    public static List<String> getIterableSplitJson(final String json,
                                                    final Class tClass) {
        // 空列表
        final String trimJson = json.trim();

        //非对象的常见 JDK 类型
        if(ClassTypeUtil.isJdk(tClass)) {
            return Instances.singleton(JsonIterableScanner.class).scan(trimJson);
        } else {
            // 对象类型循环处理
            return Instances.singleton(JsonArrayObjectScanner.class).scan(trimJson);
        }
    }
}
