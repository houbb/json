package com.github.houbb.json.support.serialize.util;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

import java.util.Currency;

/**
 * 货币 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class CurrencySerialize implements ISerialize<Currency> {

    @Override
    public String serialize(Currency object, ISerializeContext context) {
        return object.toString();
    }

}
