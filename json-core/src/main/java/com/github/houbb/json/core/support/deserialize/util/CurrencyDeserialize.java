package com.github.houbb.json.core.support.deserialize.util;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

import java.util.Currency;

/**
 * Currency 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class CurrencyDeserialize implements IDeserialize<Currency> {

    @Override
    public Currency deserialize(String json, Class<Currency> currencyClass) {
        return Currency.getInstance(json);
    }

}
