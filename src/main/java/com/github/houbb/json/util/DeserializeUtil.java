package com.github.houbb.json.util;

import com.github.houbb.heaven.util.lang.ObjectUtil;
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
     * 是否不是特殊符号
     * @param deserialize 反序列化
     * @return 是否
     * @since 0.0.6
     */
    public static boolean isNotSpecialSymbol(final IDeserialize deserialize) {
        return !isSpecialSymbol(deserialize);
    }



}
