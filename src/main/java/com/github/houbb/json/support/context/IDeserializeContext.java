package com.github.houbb.json.support.context;

import com.github.houbb.heaven.support.attr.IAttributeContext;
import com.github.houbb.json.support.config.IDeserializeConfig;

/**
 * 反序列化上下文
 * @author binbin.hou
 * @since 0.1.6
 */
public interface IDeserializeContext extends IAttributeContext {

    /**
     * 配置信息
     * @return 配置信息
     * @since 0.1.6
     */
    IDeserializeConfig config();

    /**
     * 原始对象
     * @return 目标对象
     * @since 0.1.6
     */
    String json();

    /**
     * 转换的类型
     * @param <T> 泛型
     * @return 结果
     * @since 0.1.6
     */
    <T> Class<T> type();

}
