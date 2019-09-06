package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

/**
 * Float 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class FloatDeserialize implements IDeserialize<Float> {

    @Override
    public Float deserialize(String json, Class<Float> floatClass) {
        return Float.valueOf(json);
    }

}
