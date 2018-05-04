/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.github.houbb.json.prepare.UserPrepare;

import org.junit.Test;

import java.util.Arrays;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 17:08  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class FastJsonTest {

    @Test
    public void userTest() {
        System.out.println(JSON.toJSONString(Arrays.asList(UserPrepare.getUser(), UserPrepare.getUser())));
    }

}
