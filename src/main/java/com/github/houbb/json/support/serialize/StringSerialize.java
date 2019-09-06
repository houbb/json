package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.json.api.ISerialize;

/**
 * String 序列化
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class StringSerialize implements ISerialize<String> {

    @Override
    public String serialize(String s) {
        return PunctuationConst.DOUBLE_QUOTES+s+PunctuationConst.DOUBLE_QUOTES;
    }

}
