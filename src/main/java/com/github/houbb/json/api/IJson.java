package com.github.houbb.json.api;

/**
 * JSON 接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IJson {

    /**
     * 序列化
     * @param object 对象
     * @return 序列化后的 json
     * @since 0.0.1
     */
    String serialize(final Object object);


    /**
     * 反序列化
     * @param json json 信息
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 反序列化对象
     * @since 0.0.1
     */
    <T> T deserialize(final String json, Class<T> tClass);

}
