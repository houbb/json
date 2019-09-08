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

        System.out.println(JsonBs.serialize(c));
        System.out.println(JsonBs.deserialize(json, char.class));
    }

    @Test
    public void escape2Test() {
        char c = '\\';
        final String json = "\"'\"";

        System.out.println(JsonBs.serialize(c));
        System.out.println(JsonBs.deserialize(json, char.class));
    }

}
