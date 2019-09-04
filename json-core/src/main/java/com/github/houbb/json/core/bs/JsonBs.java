package com.github.houbb.json.core.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.core.core.DefaultJson;

import java.util.List;

/**
 * Json 引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class JsonBs {

    private JsonBs(){}

    /**
     * 序列化对象
     * @param object 对象
     * @return 结果
     * @since 0.0.1
     */
    public static String serialize(Object object) {
        return Instances.threadLocal(DefaultJson.class).serialize(object);
    }

    /**
     * 反序列化对象
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 反序列化后的对象
     * @since 0.0.1
     */
    public static <T> T deserialize(String json, Class<T> tClass) {
        return Instances.threadLocal(DefaultJson.class).deserialize(json, tClass);
    }

    /**
     * 反序列化列表
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 列表
     * @since 0.0.1
     */
    public static <T> List<T> deserializeList(String json, Class<T> tClass) {
        throw new UnsupportedOperationException();
    }

}
