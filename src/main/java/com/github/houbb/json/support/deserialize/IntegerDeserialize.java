package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * Integer 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class IntegerDeserialize implements IDeserialize<Integer> {

    @Override
    public Integer deserialize(String json, Class<Integer> integerClass, IDeserializeContext context) {
        return Integer.valueOf(json);
    }

}
