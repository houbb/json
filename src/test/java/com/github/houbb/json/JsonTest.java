/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json;

import com.github.houbb.json.api.impl.JsonApiImpl;
import com.github.houbb.json.prepare.UserPrepare;

import org.junit.Test;

import java.util.Arrays;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 17:01  </pre>
 * <pre> Project: json  </pre>
 *
 * @author Administrator
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonTest {

    @Test
    public void toJson() {
        System.out.println(new JsonApiImpl().toJson(Arrays.asList(UserPrepare.getUser(), UserPrepare.getUser())));
    }

}
