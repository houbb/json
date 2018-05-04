/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.utils;

import com.github.houbb.paradise.common.util.CollectionUtil;

import java.util.List;

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
public final class CollectionUtils {

    private CollectionUtils(){}

    public static String join(List<String> stringList, String connector) {
        if(CollectionUtil.isEmpty(stringList)) {
            return null;
        }
        if(stringList.size() <= 1) {
            return stringList.get(0);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < stringList.size()-1; i++) {
            stringBuilder.append(stringList.get(i)).append(connector);
        }
        stringBuilder.append(stringList.get(stringList.size()-1));
        return stringBuilder.toString();
    }

}
