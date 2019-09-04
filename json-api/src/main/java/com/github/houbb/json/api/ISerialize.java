package com.github.houbb.json.api;

/**
 * 序列化接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface ISerialize {

    /**
     * 序列化
     * @param object 对象
     * @return 序列化后的 json
     * @since 0.0.1
     */
    String serialize(final Object object);

}
