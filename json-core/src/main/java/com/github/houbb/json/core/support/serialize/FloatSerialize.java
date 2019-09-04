package com.github.houbb.json.core.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * Float 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class FloatSerialize implements ISerialize<Float> {

    @Override
    public String serialize(Float aFloat) {
        return aFloat.toString();
    }

}
