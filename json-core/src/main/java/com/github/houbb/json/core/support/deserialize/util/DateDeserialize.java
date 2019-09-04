package com.github.houbb.json.core.support.deserialize.util;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

import java.util.Date;

/**
 * 日期反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DateDeserialize implements IDeserialize<Date> {

    @Override
    public Date deserialize(String json, Class<Date> dateClass) {
        final long value = Long.parseLong(json);
        return new Date(value);
    }

}
