package com.github.houbb.json.core.core;

import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.IJson;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.core.support.deserialize.DeserializeFactory;
import com.github.houbb.json.core.support.serialize.SerializeFactory;

/**
 * 默认 json 实现
 * 需要提供两个路由工具：
 * （1）序列化路由
 * （2）反序列化路由
 *
 * 【先死后活】
 * 首先写的比较简单，直接利用各种 if/else 判断。
 * @author binbin.hou
 * @since 0.0.1
 */
public class DefaultJson implements IJson {

    @Override
    public String serialize(Object object) {
        ISerialize serialize = SerializeFactory.getSerialize(object);
        return serialize.serialize(object);
    }

    @Override
    public <T> T deserialize(String json, Class<T> tClass) {
        IDeserialize deserialize = DeserializeFactory.getDeserialize(json, tClass);
        return (T) deserialize.deserialize(json, tClass);
    }

}
