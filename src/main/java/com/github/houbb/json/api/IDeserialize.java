package com.github.houbb.json.api;

import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * 反序列化接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IDeserialize<T> {

    /**
     * 反序列化
     * @param json json
     * @param tClass 类型
     * @param context 上下文
     * @return 结果
     * @since 0.0.1
     */
    T deserialize(String json, Class<T> tClass,
                  final IDeserializeContext context);

}
