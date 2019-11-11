package com.github.houbb.json.core;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.PrimitiveUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.IJson;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.config.IDeserializeConfig;
import com.github.houbb.json.support.config.ISerializeConfig;
import com.github.houbb.json.support.config.impl.JsonConfigs;
import com.github.houbb.json.support.deserialize.DeserializeFactory;
import com.github.houbb.json.support.serialize.SerializeFactory;

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

    /**
     * 序列化配置
     * @since 0.1.6
     */
    private ISerializeConfig serializeConfig = JsonConfigs.serializeConfig();

    /**
     * 反序列化配置
     * @since 0.1.6
     */
    private IDeserializeConfig deserializeConfig = JsonConfigs.deserializeConfig();

    public static DefaultJson serializeConfig(ISerializeConfig serializeConfig) {
        DefaultJson defaultJson = new DefaultJson();
        defaultJson.serializeConfig = serializeConfig;
        return defaultJson;
    }

    public static DefaultJson deserializeConfig(IDeserializeConfig deserializeConfig) {
        DefaultJson defaultJson = new DefaultJson();
        defaultJson.deserializeConfig = deserializeConfig;
        return defaultJson;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String serialize(Object object) {
        ISerialize serialize = SerializeFactory.getSerialize(object);
        return serialize.serialize(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(String json, Class<T> tClass) {
        IDeserialize deserialize = DeserializeFactory.getDeserialize(json, tClass);
        T result = (T) deserialize.deserialize(json, tClass);

        // 基本类型且返回类型为 null
        if(ObjectUtil.isNull(result)
            && ClassTypeUtil.isPrimitive(tClass)) {
            return (T) PrimitiveUtil.getDefaultValue(tClass);
        }

        return result;
    }

}
