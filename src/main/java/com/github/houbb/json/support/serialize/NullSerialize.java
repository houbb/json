package com.github.houbb.json.support.serialize;

import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.constant.JsonConst;

/**
 * null 序列化
 * @author binbin.hou
 * @since 0.0.1
 */
public class NullSerialize implements ISerialize {

    @Override
    public String serialize(Object object) {
        return JsonConst.NULL;
    }

}
