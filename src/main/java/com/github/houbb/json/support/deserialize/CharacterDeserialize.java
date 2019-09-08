package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;

/**
 * Character 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class CharacterDeserialize implements IDeserialize<Character>, ISpecialSymbol {

    @Override
    public Character deserialize(String json, Class<Character> characterClass) {
        String chars = json.substring(1, json.length()-1);
        return chars.charAt(0);
    }

}
