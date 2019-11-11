package com.github.houbb.json.support.deserialize;

import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * null 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class NullDeserialize implements IDeserialize {

    @Override
    public Object deserialize(String json, Class aClass, IDeserializeContext context) {
        return null;
    }

}
