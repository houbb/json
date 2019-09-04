package com.github.houbb.json.core.support.serialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigDecimalSerialize implements ISerialize<BigDecimal> {

    @Override
    public String serialize(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }

}
