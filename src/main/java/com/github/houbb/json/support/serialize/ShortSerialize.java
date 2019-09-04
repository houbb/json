package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * short 序列化
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ShortSerialize implements ISerialize<Short> {

    @Override
    public String serialize(Short aShort) {
        return aShort.toString();
    }

}
