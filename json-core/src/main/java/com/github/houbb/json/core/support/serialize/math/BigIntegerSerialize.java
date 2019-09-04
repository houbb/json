package com.github.houbb.json.core.support.serialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

import java.math.BigInteger;

/**
 * BigInteger序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigIntegerSerialize implements ISerialize<BigInteger> {

    @Override
    public String serialize(BigInteger bigInteger) {
        return bigInteger.toString();
    }

}
