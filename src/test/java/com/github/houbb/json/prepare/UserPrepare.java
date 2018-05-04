/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.prepare;

import com.github.houbb.json.model.User;

import java.math.BigDecimal;
import java.util.Date;

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
public class UserPrepare {

    public static User getUser() {
        User user = new User();
        user.setAmount(BigDecimal.ZERO);
        user.setId(1);
        user.setName("hello");
        user.setTime(new Date());
        return user;
    }

}
