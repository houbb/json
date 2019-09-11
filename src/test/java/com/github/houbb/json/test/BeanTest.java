package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.model.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * 序列化 测试
 * @author binbin.hou
 * @since 0.0.7
 */
public class BeanTest {

    @Test
    public void userTest() {
        User user = new User();
        user.age(10).name("wiki").birthday(new Date(1568196960491L)).score(123.d).sex('g');

        final String json = "{\"name\":\"wiki\",\"age\":10,\"score\":123.0,\"sex\":\"g\",\"birthday\":1568196960491}";
        Assert.assertEquals(json, JsonBs.serialize(user));

        User user2 = JsonBs.deserialize(json, User.class);
        System.out.println(user2);
    }

}
