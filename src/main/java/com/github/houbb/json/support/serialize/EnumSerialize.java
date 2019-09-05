package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.json.api.ISerialize;

/**
 * 枚举值序列化
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class EnumSerialize implements ISerialize<Enum> {

    @Override
    public String serialize(Enum anEnum) {
        return PunctuationConst.DOUBLE_QUOTES+anEnum.name()+PunctuationConst.DOUBLE_QUOTES;
    }

}
