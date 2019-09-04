package com.github.houbb.json.core.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

/**
 * 布尔值反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BooleanDeserialize implements IDeserialize<Boolean> {

    @Override
    public Boolean deserialize(String json, Class<Boolean> booleanClass) {
        return Boolean.valueOf(json);
    }

}
