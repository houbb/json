package com.github.houbb.json.support.serialize.math;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

import java.math.BigDecimal;

/**
 * BigDecimal 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BigDecimalSerialize implements ISerialize<BigDecimal> {

    @Override
    public String serialize(BigDecimal object, ISerializeContext context) {
        return object.toString();
    }

}
