package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;
import com.github.houbb.json.support.context.impl.SerializeContext;

/**
 * Byte 序列化
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ByteSerialize implements ISerialize<Byte> {

    @Override
    public String serialize(Byte object, ISerializeContext context) {
        return object.toString();
    }

}
