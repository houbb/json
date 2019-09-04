package com.github.houbb.json.core.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * Integer 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class IntegerSerialize implements ISerialize<Integer> {

    @Override
    public String serialize(Integer integer) {
        return integer.toString();
    }

}
