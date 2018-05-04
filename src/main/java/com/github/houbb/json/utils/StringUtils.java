/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.utils;

import com.github.houbb.paradise.common.util.ObjectUtil;
import com.github.houbb.paradise.common.util.StringUtil;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 16:37  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class StringUtils {

    /**
     * 转换为字符串
     * @param object
     * @return
     */
    public static String castToString(Object object) {
        if(ObjectUtil.isNull(object)) {
            return null;
        }
        return object.toString();
    }

    /**
     * 转义
     * @param keyword
     * @return
     */
    public static String escapeRegExSpecialWord(String keyword) {
        if (StringUtil.isNotEmpty(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    /**
     * 两边添加
     * @param string
     * @param appendString
     * @return
     */
    public static String bothAppend(final String string, final String appendString) {
        final String escape = StringUtils.escapeRegExSpecialWord(appendString);
        return escape+string+escape;
    }

    public static String bothAppendDoubleQuote(final String string) {
        return bothAppend(string, "\"");
    }

}
