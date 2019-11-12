package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.support.config.IDeserializeConfig;
import com.github.houbb.json.support.config.ISerializeConfig;
import com.github.houbb.json.support.config.impl.DeserializeConfig;
import com.github.houbb.json.support.config.impl.SerializeConfig;
import com.github.houbb.json.test.model.Book;
import com.github.houbb.json.test.model.DefaultBeanDefinition;
import com.github.houbb.json.test.model.NotFieldBook;
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
        Assert.assertEquals(user.toString(), user2.toString());
    }

    /**
     * 定义测试
     * @since 0.1.2
     */
    @Test
    public void defineTest() {
        final String json = "[\n" +
                "{\"name\":\"apple\",\"className\":\"com.github.houbb.ioc.test.service.Apple\"}\n" +
                "]";
        System.out.println(JsonBs.deserialize(json, DefaultBeanDefinition.class));
    }

    /**
     * null 值是否保留测试
     * @since 0.1.6
     */
    @Test
    public void nullRemainsTest() {
        Book book = new Book();

        String json = JsonBs.serialize(book);
        Assert.assertEquals("{}", json);

        final ISerializeConfig config = SerializeConfig.newInstance().nullRemains(true);
        String nullJson = JsonBs.serialize(book, config);
        Assert.assertEquals("{\"name\":null}", nullJson);
    }

    @Test
    public void bookNotFieldBasedTest() {
        NotFieldBook book = new NotFieldBook();
        book.setName("hello");
        book.setChina(true);

        final String json = "{\"name\":\"hello\",\"china\":true}";
        Assert.assertEquals(json, JsonBs.serialize(book, SerializeConfig.newInstance().fieldBased(false)));

        final IDeserializeConfig deserializeConfig = DeserializeConfig.newInstance().fieldBased(false);
        NotFieldBook book2 = JsonBs.deserialize(json, NotFieldBook.class, deserializeConfig);
        Assert.assertEquals("hello", book2.getName());
        Assert.assertTrue(book2.isChina());
    }

}
