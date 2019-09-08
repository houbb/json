package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.json.api.ISerialize;

/**
 * Character 序列化
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class CharacterSerialize implements ISerialize<Character> {

    @Override
    public String serialize(Character character) {
        final String escape = getEscapeChars(character);
        return PunctuationConst.DOUBLE_QUOTES+escape+PunctuationConst.DOUBLE_QUOTES;
    }

    /**
     * 处理转移字符
     * @param character 字符
     * @return 结果
     * @since 0.0.4
     */
    private String getEscapeChars(Character character) {
        if('\\' == character) {
            return "\\\\";
        }
        if('\'' == character) {
            return "\\\'";
        }
        return character.toString();
    }

}
