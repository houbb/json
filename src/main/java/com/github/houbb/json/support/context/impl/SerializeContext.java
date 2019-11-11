package com.github.houbb.json.support.context.impl;

import com.github.houbb.heaven.support.attr.impl.AttributeContext;
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
     * 配置信息
     * @since 0.1.6
     */
    private ISerializeConfig config;

    /**
     * 原始对象
     * @since 0.1.6
     */
    private Object object;

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
        return this;
    }

    @Override
    public String toString() {
        return "SerializeContext{" +
                "config=" + config +
                ", object=" + object +
                '}';
    }

}
