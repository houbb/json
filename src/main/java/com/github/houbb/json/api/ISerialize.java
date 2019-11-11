package com.github.houbb.json.api;

import com.github.houbb.json.support.context.ISerializeContext;

/**
 * 序列化接口
 * @author binbin.hou
 * @since 0.0.1
 * @param <T> 泛型
 */
public interface ISerialize<T> {

    /**
     * 序列化
     * @param object 原始待序列化对象
     * @param context 对象
     * @return 序列化后的 json
     * @since 0.0.1
     */
    String serialize(final T object, final ISerializeContext context);

}
