package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * String 序列化
 * TODO: 转义字符的问题
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class StringSerialize implements ISerialize<String> {

    @Override
    public String serialize(String object, ISerializeContext context) {
        return PunctuationConst.DOUBLE_QUOTES+object+PunctuationConst.DOUBLE_QUOTES;
    }

}
