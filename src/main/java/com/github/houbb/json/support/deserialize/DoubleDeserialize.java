package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * Double 反序列对象
 * TODO: double/float 是否需要 f/d 后缀
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DoubleDeserialize implements IDeserialize<Double> {

    @Override
    public Double deserialize(String json, Class<Double> doubleClass, IDeserializeContext context) {
        return Double.valueOf(json);
    }
}
