package com.github.houbb.json.support.config.impl;

import com.github.houbb.json.support.config.IDeserializeConfig;

/**
 * 反序列化配置接口
 * @author binbin.hou
 * @since 0.1.6
 */
public class DeserializeConfig implements IDeserializeConfig {

    /**
     * 是否基于字段进行处理
     * @since 0.1.6
     */
    private boolean fieldBased = true;

    /**
     * 新建对象实例
     * @return 对象实例
     * @since 0.1.6
     */
    public static DeserializeConfig newInstance() {
        return new DeserializeConfig();
    }

    @Override
    public boolean fieldBased() {
        return fieldBased;
    }

    public DeserializeConfig fieldBased(boolean fieldBased) {
        this.fieldBased = fieldBased;
        return this;
    }

    @Override
    public String toString() {
        return "DeserializeConfig{" +
                "fieldBased=" + fieldBased +
                '}';
    }

}
