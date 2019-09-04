package com.github.houbb.json.core.support.deserialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.core.constant.JsonConst;

import java.util.HashMap;
import java.util.Map;

/**
 * 反序列化工厂类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class DeserializeFactory {

    /**
     * @since 0.0.2
     */
    private DeserializeFactory(){}

    /**
     * 类实例映射
     * @since 0.0.2
     */
    private static final Map<Class, IDeserialize> CLASS_INSTANCE_MAP = new HashMap<>();

    static {
        CLASS_INSTANCE_MAP.put(String.class, Instances.singleton(StringDeserialize.class));
    }

    /**
     * 获取反序列化实现
     * @param json json
     * @param clazz 类
     * @return 结果
     * @since 0.0.1
     */
    public static IDeserialize getDeserialize(final String json, final Class clazz) {
        if(JsonConst.NULL.equals(json)) {
            return Instances.singleton(NullDeserialize.class);
        }

        //@since 0.0.2
        return CLASS_INSTANCE_MAP.get(clazz);
    }

}
