package com.github.houbb.json.core.support.deserialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

import java.math.BigInteger;

/**
 * BigInteger 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigIntegerDeserialize implements IDeserialize<BigInteger> {

    @Override
    public BigInteger deserialize(String json, Class<BigInteger> bigIntegerClass) {
        return BigInteger.valueOf(Long.parseLong(json));
    }

}
