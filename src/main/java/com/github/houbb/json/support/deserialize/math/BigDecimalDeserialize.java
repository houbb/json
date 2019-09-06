package com.github.houbb.json.support.deserialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigDecimalDeserialize implements IDeserialize<BigDecimal> {

    @Override
    public BigDecimal deserialize(String json, Class<BigDecimal> bigDecimalClass) {
        return BigDecimal.valueOf(Double.valueOf(json));
    }

}
