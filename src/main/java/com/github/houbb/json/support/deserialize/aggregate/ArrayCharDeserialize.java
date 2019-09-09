package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonIterableConst;

/**
 * char 数组的反序列化
 *
 * @author binbin.hou
 * @since 0.0.4
 */
public class ArrayCharDeserialize implements IDeserialize<char[]>, ISpecialSymbol {

    @Override
    public char[] deserialize(String json, Class<char[]> aClass) {
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return ArrayPrimitiveUtil.CHAR_EMPTY;
        }

        // 直接根据逗号分隔，开始和结尾要去掉。
        String contentJson = trimJson.substring(1, trimJson.length()-1);
        String[] strings = contentJson.split(PunctuationConst.COMMA);

        char[] resultArray = new char[strings.length];
        for(int i = 0; i < strings.length; i++) {
            String itemJson = strings[i];
            char item = JsonBs.deserialize(itemJson, char.class);
            resultArray[i] = item;
        }
        return resultArray;
    }

}
