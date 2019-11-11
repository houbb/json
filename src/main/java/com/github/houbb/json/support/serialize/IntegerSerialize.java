package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * Integer 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class IntegerSerialize implements ISerialize<Integer> {

    @Override
    public String serialize(Integer object, ISerializeContext context) {
        return object.toString();
    }

}
