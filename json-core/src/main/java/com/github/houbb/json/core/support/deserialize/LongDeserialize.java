package com.github.houbb.json.core.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;

/**
 * Long 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class LongDeserialize implements IDeserialize<Long> {

    @Override
    public Long deserialize(String json, Class<Long> longClass) {
        return Long.valueOf(json);
    }

}
