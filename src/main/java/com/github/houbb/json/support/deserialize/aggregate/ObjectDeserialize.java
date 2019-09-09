package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.NumUtil;
import com.github.houbb.heaven.util.util.Optional;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;

/**
 * 针对普通对象的反序列化
 * 直接返回属性本身。
 * 可以根据细节处理。
 *
 * 比如字符串-日期。
 *
 * 布尔类型的判断。
 *
 * 数字,float，double。
 *
 * null undefined NAN
 *
 * hex 字节
 *
 * 等等.
 *
 * 为了提升性能。可以将分析的过程放在前面，直接分析处理。
 * @author binbin.hou
 * @since 0.0.5
 */
public class ObjectDeserialize implements IDeserialize {

    @Override
    public Object deserialize(String json, Class aClass) {
        if("true".equals(json)) {
            return true;
        }
        if("false".equals(json)) {
            return false;
        }
        if("null".equals(json)
            || "undefined".equals(json)
            || "NaN".equals(json)) {
            return null;
        }
        // 整数
        Optional<Integer> integerOptional = NumUtil.toInteger(json);
        if(integerOptional.isPresent()) {
            return integerOptional.get();
        }
        // long
        Optional<Long> longOptional = NumUtil.toLong(json);
        if(longOptional.isPresent()) {
            return longOptional.get();
        }
        // decimal/double
        Optional<Double> doubleOptional = NumUtil.toDouble(json);
        if(doubleOptional.isPresent()) {
            return doubleOptional.get();
        }

        // 字符串
        // 字符串-日期
        if(isStringType(json)) {
            return JsonBs.deserialize(json, String.class);
        }

        // 16 进制，返回字节流。
        if(NumUtil.isHex(json)) {
            return json.getBytes();
        }

        throw new JsonRuntimeException(JsonRespCode.DES_UNSUPPORT_TYPE);
    }

    /**
     * 是否为字符串类型
     * @param json json
     * @return 是否
     */
    private boolean isStringType(final String json) {
        return json.charAt(0) == PunctuationConst.C_DOUBLE_QUOTES
                && json.charAt(json.length()-1) == PunctuationConst.C_DOUBLE_QUOTES;
    }

}
