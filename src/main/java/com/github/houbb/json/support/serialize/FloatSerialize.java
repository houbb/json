package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * Float 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class FloatSerialize implements ISerialize<Float> {

    @Override
    public String serialize(Float object, ISerializeContext context) {
        return object.toString();
    }

}
