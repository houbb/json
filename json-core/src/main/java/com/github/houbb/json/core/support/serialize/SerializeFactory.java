package com.github.houbb.json.core.support.serialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.json.api.ISerialize;

/**
 * 序列化工厂
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SerializeFactory {

    private SerializeFactory(){}

    /**
     * 获取序列化实现
     * @param object  对象
     * @return 对应实现
     */
    public static ISerialize getSerialize(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Instances.singleton(NullSerialize.class);
        }
        return null;
    }

}
