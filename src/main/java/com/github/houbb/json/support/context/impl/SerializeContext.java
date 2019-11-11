package com.github.houbb.json.support.context.impl;

import com.github.houbb.heaven.support.attr.impl.AttributeContext;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.config.ISerializeConfig;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * 序列化上下文
 *
 * 核心目的：
 * （1）便于后续拓展，而保证接口不变化。
 * （2）保证 scan-once
 *
 * @author binbin.hou
 * @since 0.1.6
 */
public class SerializeContext extends AttributeContext implements ISerializeContext {

    /**
     * 序列化实现
     * @since 0.1.16
     */
    private ISerialize serialize;

    /**
     * 配置信息
     * @since 0.1.6
     */
    private ISerializeConfig config;

    /**
     * 原始对象
     * @since 0.1.6
     */
    private Object object;

    /**
     * 对象类型
     * @since 0.1.6
     */
    private Class type;

    /**
     * 新建实例
     * @return 上下文
     * @since 0.1.6
     */
    public static SerializeContext newInstance() {
        return new SerializeContext();
    }

    public ISerialize serialize() {
        return serialize;
    }

    public SerializeContext serialize(ISerialize serialize) {
        this.serialize = serialize;
        return this;
    }

    @Override
    public ISerializeConfig config() {
        return config;
    }

    public SerializeContext config(ISerializeConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public Object object() {
        return object;
    }

    public SerializeContext object(Object object) {
        this.object = object;
        this.type = ObjectUtil.getClass(object);
        return this;
    }

    @Override
    public Class type() {
        return type;
    }

}
