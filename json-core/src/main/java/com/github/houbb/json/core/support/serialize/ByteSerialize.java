package com.github.houbb.json.core.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * Byte 序列化
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ByteSerialize implements ISerialize<Byte> {

    @Override
    public String serialize(Byte aByte) {
        return aByte.toString();
    }

}
