package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * 布尔序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BooleanSerialize implements ISerialize<Boolean> {

    @Override
    public String serialize(Boolean object, ISerializeContext context) {
        return object.toString();
    }

}
