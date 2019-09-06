package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

/**
 * Byte 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class ByteDeserialize implements IDeserialize<Byte> {

    @Override
    public Byte deserialize(String json, Class<Byte> byteClass) {
        return Byte.valueOf(json);
    }

}
