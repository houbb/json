package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * char 测试
 * @author binbin.hou
 * @since 0.0.4
 */
public class CharTest {

    @Test
    public void escapeTest() {
        char c = '\'';
        final String json = "\"'\"";

        Assert.assertEquals(json, JsonBs.serialize(c));
        assert c == JsonBs.deserialize(json, char.class);
    }

}
