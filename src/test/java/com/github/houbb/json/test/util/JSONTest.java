package com.github.houbb.json.test.util;

import com.github.houbb.json.test.model.User;
import com.github.houbb.json.util.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.1.9
 */
public class JSONTest {

    /**
     * @since 0.1.9
     */
    @Test
    public void toJsonStringTest() {
        User user = new User();
        user.age(10).name("wiki").birthday(new Date(1568196960491L)).score(123.d).sex('g');

        final String json = "{\"name\":\"wiki\",\"age\":10,\"score\":123.0,\"sex\":\"g\",\"birthday\":1568196960491}";
        Assert.assertEquals(json, JSON.toJSONString(user));
    }

    /**
     * @since 0.1.9
     */
    @Test
    public void parseObjectTest() {
        final String json = "{\"name\":\"wiki\",\"age\":10,\"score\":123.0,\"sex\":\"g\",\"birthday\":1568196960491}";

        User user = JSON.parseObject(json, User.class);
        Assert.assertEquals("User{name='wiki', age=10, score=123.0, sex=g, birthday=Wed Sep 11 18:16:00 CST 2019}", user.toString());
    }

    /**
     * @since 0.1.9
     */
    @Test
    public void parseArrayTest() {
        final String json = "[{\"name\":\"wiki\",\"age\":10,\"score\":123.0,\"sex\":\"g\",\"birthday\":1568196960491}]";

        List<User> users = JSON.parseArray(json, User.class);
        Assert.assertEquals("[User{name='wiki', age=10, score=123.0, sex=g, birthday=Wed Sep 11 18:16:00 CST 2019}]", users.toString());
    }

}
