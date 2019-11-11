package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.constant.JsonConst;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * null 序列化
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class NullSerialize implements ISerialize {

    @Override
    public String serialize(Object object, ISerializeContext context) {
        return JsonConst.NULL;
    }

}
