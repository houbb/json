package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.context.ISerializeContext;

/**
 * Character 序列化
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class CharacterSerialize implements ISerialize<Character> {

    @Override
    public String serialize(Character object, ISerializeContext context) {
        return PunctuationConst.DOUBLE_QUOTES+object+PunctuationConst.DOUBLE_QUOTES;
    }

}
