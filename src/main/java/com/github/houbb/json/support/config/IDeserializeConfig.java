package com.github.houbb.json.support.config;

/**
 * 反序列化配置接口
 * @author binbin.hou
 * @since 0.1.6
 */
public interface IDeserializeConfig {

    /**
     * 是否基于字段进行处理
     * @return 是否
     * @since 0.1.6
     */
    boolean fieldBased();

}
