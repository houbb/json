package com.github.houbb.json.support.config.impl;

import com.github.houbb.json.support.config.IDeserializeConfig;
import com.github.houbb.json.support.config.ISerializeConfig;

/**
 * JSON 配置工具类
 * @author binbin.hou
 * @since 0.1.6
 */
public final class JsonConfigs {

    private JsonConfigs(){}

    /**
     * 序列化配置
     * @since 0.1.6
     */
    private static final ISerializeConfig SERIALIZE_CONFIG;

    /**
     * 反序列化默认配置
     * @since 0.1.6
     */
    private static final IDeserializeConfig DESERIALIZE_CONFIG;

    static {
        SERIALIZE_CONFIG = SerializeConfig.newInstance();
        DESERIALIZE_CONFIG = DeserializeConfig.newInstance();
    }

    /**
     * 序列化配置
     * @return 序列化配置
     * @since 0.1.6
     */
    public static ISerializeConfig serializeConfig() {
        return SERIALIZE_CONFIG;
    }

    /**
     * 反序列化配置
     * @return 序列化配置
     * @since 0.1.6
     */
    public static IDeserializeConfig deserializeConfig() {
        return DESERIALIZE_CONFIG;
    }

}
