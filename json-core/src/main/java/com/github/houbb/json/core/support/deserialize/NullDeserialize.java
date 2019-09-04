package com.github.houbb.json.core.support.deserialize;

import com.github.houbb.json.api.IDeserialize;

/**
 * null 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class NullDeserialize implements IDeserialize {

    @Override
    public Object deserialize(String json, Class aClass) {
        return null;
    }

}
