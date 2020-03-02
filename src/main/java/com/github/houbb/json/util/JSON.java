package com.github.houbb.json.util;

import com.github.houbb.json.bs.JsonBs;

import java.util.List;

/**
 * 工具类
 *
 * 兼容 FastJSON 的 api
 * @author binbin.hou
 * @since 0.1.9
 */
public final class JSON {

    private JSON(){}

    /**
     * 转换为对象
     * @param text json 信息
     * @param clazz 类型
     * @param <T> 泛型
     * @return 结果
     * @since 0.1.9
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JsonBs.deserialize(text, clazz);
    }

    /**
     * 转换为对象列表
     * @param text json 信息
     * @param clazz 类型
     * @param <T> 泛型
     * @return 结果
     * @since 0.1.9
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JsonBs.deserializeArray(text, clazz);
    }

    /**
     * 转换为 json 字符串
     * @param object 对象信息
     * @return json 信息
     * @since 0.1.9
     */
    public static String toJSONString(Object object) {
        return JsonBs.serialize(object);
    }

}
