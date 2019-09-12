package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * String 测试
 * @author binbin.hou
 * @since 0.0.2
 */
public class StringTest {

    @Test
    public void commonTest() {
        final String string = "123";
        Assert.assertEquals("\"123\"", JsonBs.serialize(string));

        final String json = "\"123\"";
        Assert.assertEquals(string, JsonBs.deserialize(json, String.class));
    }

    @Test
    public void escapeTest() {
        final String string = "\"123";
        Assert.assertEquals("\"\"123\"", JsonBs.serialize(string));

        final String json = "\"123\"";
        Assert.assertEquals("123", JsonBs.deserialize(json, String.class));
    }

}
