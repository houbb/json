package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * Long 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class LongDeserialize implements IDeserialize<Long> {

    @Override
    public Long deserialize(String json, Class<Long> longClass, IDeserializeContext context) {
        return Long.valueOf(json);
    }

}
