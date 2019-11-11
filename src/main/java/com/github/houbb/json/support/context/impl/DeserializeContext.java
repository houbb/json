package com.github.houbb.json.support.context.impl;

import com.github.houbb.heaven.support.attr.impl.AttributeContext;
import com.github.houbb.json.support.config.IDeserializeConfig;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * 反序列化上下文
 * @author binbin.hou
 * @since 0.1.6
 */
public class DeserializeContext extends AttributeContext implements IDeserializeContext {

    /**
     * 配置信息
     * @since 0.1.6
     */
    private IDeserializeConfig config;

    /**
     * json 信息
     * @since 0.1.6
     */
    private String json;

    /**
     * 转换的类型
     * @since 0.1.6
     */
    private Class<?> type;

    @Override
    public IDeserializeConfig config() {
        return config;
    }

    public DeserializeContext config(IDeserializeConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public String json() {
        return json;
    }

    public DeserializeContext json(String json) {
        this.json = json;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> type() {
        return (Class<T>) type;
    }

    public <T> DeserializeContext type(Class<T> type) {
        this.type = type;
        return this;
    }

}
