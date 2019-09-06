package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * 布尔序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class BooleanSerialize implements ISerialize<Boolean> {

    @Override
    public String serialize(Boolean aBoolean) {
        return aBoolean.toString();
    }

}
