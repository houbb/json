package com.github.houbb.json.core.support.serialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.json.api.ISerialize;
import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化工厂
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SerializeFactory {

    private SerializeFactory(){}

    /**
     * 类与实例的映射关系
     * @since 0.0.2
     */
    private static final Map<Class, ISerialize> CLASS_INSTANCE_MAP = new HashMap<>();

    static {
        CLASS_INSTANCE_MAP.put(String.class, Instances.singleton(StringSerialize.class));
    }

    /**
     * 获取序列化实现
     * （1）null
     * （2）8大基本类型
     * （3）对象
     * （4）集合/数组/map
     * @param object  对象
     * @return 对应实现
     */
    public static ISerialize getSerialize(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Instances.singleton(NullSerialize.class);
        }
        final Class clazz = object.getClass();

        // 字符串
        return CLASS_INSTANCE_MAP.get(clazz);
    }

}
