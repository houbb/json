package com.github.houbb.json.support.config.impl;

import com.github.houbb.json.support.config.ISerializeConfig;

/**
 * 序列化配置指定
 * @author binbin.hou
 * @since 0.1.6
 */
public class SerializeConfig implements ISerializeConfig {

    /**
     * 是否基于字段进行序列化处理
     * @since 0.1.6
     */
    private boolean fieldBased = true;

    /**
     * 是否保留 null 值
     * @since 0.1.6
     */
    private boolean nullRemains = false;

    /**
     * 是否美化输出
     * @since 0.1.6
     */
    private boolean pretty = false;

    private SerializeConfig(){}

    /**
     * 新建一个实例
     * @return 实例
     * @since 0.1.6
     */
    public static SerializeConfig newInstance() {
        return new SerializeConfig();
    }

    @Override
    public boolean fieldBased() {
        return fieldBased;
    }

    public SerializeConfig fieldBased(boolean fieldBased) {
        this.fieldBased = fieldBased;
        return this;
    }

    @Override
    public boolean nullRemains() {
        return nullRemains;
    }

    public SerializeConfig nullRemains(boolean nullRemains) {
        this.nullRemains = nullRemains;
        return this;
    }

    @Override
    public boolean pretty() {
        return pretty;
    }

    public SerializeConfig pretty(boolean pretty) {
        this.pretty = pretty;
        return this;
    }

    @Override
    public String toString() {
        return "SerializeConfig{" +
                "fieldBased=" + fieldBased +
                ", nullRemains=" + nullRemains +
                ", pretty=" + pretty +
                '}';
    }

}
