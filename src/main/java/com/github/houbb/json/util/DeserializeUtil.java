package com.github.houbb.json.util;

import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;

/**
 * <p> 反序列化工具类 </p>
 *
 * <pre> Created: 2019/9/10 10:37 PM  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @since 0.0.6
 */
public final class DeserializeUtil {

    private DeserializeUtil(){}

    /**
     * 是否为特殊符号
     * @param deserialize 反序列化
     * @return 是否
     * @since 0.0.6
     */
    public static boolean isSpecialSymbol(final IDeserialize deserialize) {
        if(ObjectUtil.isNull(deserialize)) {
            return false;
        }

        return deserialize instanceof ISpecialSymbol;
    }

    /**
     * 过滤特殊符号的双引号
     *
     * （1）如果以 【"】 开始且以【"】结束，则直接去头和去尾巴。
     *  特殊情况只有一个【"】也直接去掉。
     * （2）其他情况原样返回。
     * @param originalChars 原始字符串
     * @return 结果信息
     * @see ISpecialSymbol 特殊符号
     * @since 0.1.5
     */
    public static String trimDoubleQuotes(final String originalChars) {
        if(StringUtil.isEmpty(originalChars)) {
            return originalChars;
        }
        if(originalChars.equals(PunctuationConst.DOUBLE_QUOTES)) {
            return StringUtil.EMPTY;
        }
        if(originalChars.startsWith(PunctuationConst.DOUBLE_QUOTES)
            && originalChars.endsWith(PunctuationConst.DOUBLE_QUOTES)) {
            return  originalChars.substring(1, originalChars.length()-1);
        }

        return originalChars;
    }

    /**
     * 是否不是特殊符号
     * @param deserialize 反序列化
     * @return 是否
     * @since 0.0.6
     */
    public static boolean isNotSpecialSymbol(final IDeserialize deserialize) {
        return !isSpecialSymbol(deserialize);
    }

}
