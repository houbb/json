package com.github.houbb.json.support.deserialize;

import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;

/**
 * String 反序列对象
 *
 * （1）实际反序列要更加复杂
 * （2）支持 123 "123" '123' 这三种方式
 * （3）其他如果不闭合的情况，则直接报错。此处不进行处理。
 * @author binbin.hou
 * @since 0.0.2
 */
public class StringDeserialize implements IDeserialize<String> , ISpecialSymbol {

    @Override
    public String deserialize(String json, Class<String> stringClass) {
        return json.substring(1, json.length()-1);
    }

}
