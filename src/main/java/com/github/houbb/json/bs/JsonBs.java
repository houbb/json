package com.github.houbb.json.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.api.IJson;
import com.github.houbb.json.core.DefaultJson;

import java.util.List;

/**
 * Json 引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public class JsonBs {

    /**
     * 默认 json 实现
     */
    private IJson json = Instances.singleton(DefaultJson.class);

    /**
     * 序列化对象
     * @param object 对象
     * @return 结果
     */
    public static String serialize(Object object) {
        return null;
    }

    /**
     * 反序列化对象
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String json, Class<T> tClass) {
        return null;
    }

    /**
     * 反序列化列表
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 列表
     */
    public static <T> List<T> deserializeList(String json, Class<T> tClass) {
        return null;
    }

}
