package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;

/**
 * Long 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class LongSerialize implements ISerialize<Long> {

    @Override
    public String serialize(Long aLong) {
        return aLong.toString();
    }

}
