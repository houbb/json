package com.github.houbb.json.core.support.serialize.util;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

import java.util.Date;

/**
 * 日期序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DateSerialize implements ISerialize<Date> {

    @Override
    public String serialize(Date date) {
        return String.valueOf(date.getTime());
    }

}
