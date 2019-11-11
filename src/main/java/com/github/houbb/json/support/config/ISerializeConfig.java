package com.github.houbb.json.support.config;

/**
 * 序列化配置接口
 * @author binbin.hou
 * @since 0.1.6
 */
public interface ISerializeConfig {

    /**
     * 是否基于字段进行处理
     * @return 是否
     * @since 0.1.6
     */
    boolean fieldBased();

    /**
     * 空值是否输出为序列化结果
     * @return 是否
     * @since 0.1.6
     */
    boolean nullRemains();

    /**
     * 是否美化输出
     * @return 是否
     * @since 0.1.6
     */
    boolean pretty();

}
