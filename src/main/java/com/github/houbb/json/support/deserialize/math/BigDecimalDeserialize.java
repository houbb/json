package com.github.houbb.json.support.deserialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

import java.math.BigDecimal;

/**
 * BigDecimal 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigDecimalDeserialize implements IDeserialize<BigDecimal> {

    @Override
    public BigDecimal deserialize(String json, Class<BigDecimal> bigDecimalClass, IDeserializeContext context) {
        return BigDecimal.valueOf(Double.valueOf(json));
    }

}
