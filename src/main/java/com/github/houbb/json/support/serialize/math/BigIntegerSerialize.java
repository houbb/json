package com.github.houbb.json.support.serialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

import java.math.BigInteger;

/**
 * BigInteger序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigIntegerSerialize implements ISerialize<BigInteger> {

    @Override
    public String serialize(BigInteger object, ISerializeContext context) {
        return object.toString();
    }

}
