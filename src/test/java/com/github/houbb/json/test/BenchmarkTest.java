package com.github.houbb.json.test;

import com.alibaba.fastjson.JSON;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.model.User;
import org.junit.Test;

import java.util.Date;

/**
 * 可以发现，这里 fast-json 就是比较快，至于为什么这么快。
 * （1）字段获取，通过 asm
 * （2）字符串拼接，使用技巧。而不是 StringBuilder。
 *
 * @author binbin.hou
 * @since 0.1.6
 */
public class BenchmarkTest {

    /**
     * 10w次：
     *
     * Cost Time: 308   V-0.1.7
     */
    @Test
    public void jsonBsSerializeTest() {
        User user = buildUser();

        final long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            JsonBs.serialize(user);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Cost Time: " + (endTime-startTime));
    }


    /**
     * 1w 次
     * Cost Time: 276
     */
    @Test
    public void fastJsonSerializeTest() {
        User user = buildUser();

        final long startTime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            JSON.toJSONString(user);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Cost Time: " + (endTime-startTime));
    }

    /**
     * 10w 次
     */
    @Test
    public void jsonBsDeserializeTest() {

    }


    /**
     * 构建对象
     * @return 用户对象
     * @since 0.1.6
     */
    private User buildUser() {
        User user = new User();
        user.age(10).name("wiki").birthday(new Date(1568196960491L)).score(123.d).sex('g');
        return user;
    }

}
