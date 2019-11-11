package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.support.context.IDeserializeContext;

/**
 * Short 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class ShortDeserialize implements IDeserialize<Short> {

    @Override
    public Short deserialize(String json, Class<Short> shortClass, IDeserializeContext context) {
        return Short.valueOf(json);
    }

}
