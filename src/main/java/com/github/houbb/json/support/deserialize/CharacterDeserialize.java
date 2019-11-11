package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;
import com.github.houbb.json.support.context.IDeserializeContext;
import com.github.houbb.json.util.DeserializeUtil;

/**
 * Character 反序列对象
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class CharacterDeserialize implements IDeserialize<Character>, ISpecialSymbol {

    @Override
    public Character deserialize(String json, Class<Character> characterClass, IDeserializeContext context) {
        String chars = DeserializeUtil.trimDoubleQuotes(json);

        if(StringUtil.isEmpty(chars)) {
            return null;
        }

        return chars.charAt(0);
    }

}
