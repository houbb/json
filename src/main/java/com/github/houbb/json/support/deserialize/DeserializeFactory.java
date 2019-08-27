package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonConst;

/**
 * 反序列化工厂类
 * @author binbin.hou
 * @since 0.0.1
 */
public class DeserializeFactory {

    /**
     * 获取反序列化实现
     * @param json json
     * @param clazz 类
     * @return 结果
     */
    public static IDeserialize getDeserialize(final String json, final Class clazz) {
        if(JsonConst.NULL.equals(json)) {
            return Instances.singleton(NullDeserialize.class);
        }
        return null;
    }

}
