package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.model.User;

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
        user.age(10).name("wiki").birthday(new Date()).score(123.d).sex('g');

        System.out.println(JsonBs.serialize(user));
    }

}
