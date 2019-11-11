package com.github.houbb.json.support.serialize.util;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

import java.util.Date;

/**
 * 日期序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DateSerialize implements ISerialize<Date> {

    @Override
    public String serialize(Date object, ISerializeContext context) {
        return String.valueOf(object.getTime());
    }

}
