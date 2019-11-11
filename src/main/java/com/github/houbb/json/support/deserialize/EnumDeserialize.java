package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * Enum 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class EnumDeserialize implements IDeserialize<Enum> {

    @Override
    public Enum deserialize(String json, Class<Enum> enumClass, IDeserializeContext context) {
        final String enumName = json.substring(1, json.length()-1);
        return Enum.valueOf(enumClass, enumName);
    }

}
