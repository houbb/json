package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 数组 测试
 * @author binbin.hou
 * @since 0.0.4
 */
public class ArrayTest {

    @Test
    public void stringEmptyTest() {
        String[] strings = new String[]{};
        String json = "[]";

        Assert.assertEquals(json, JsonBs.serialize(strings));
        Assert.assertEquals(strings, JsonBs.deserialize(json, String[].class));
    }

    @Test
    public void stringTest() {
        String[] strings = new String[]{"a", "b", "c"};
        final String json = "[\"a\",\"b\",\"c\"]";
        Assert.assertEquals(json, JsonBs.serialize(strings));
        Assert.assertEquals(strings, JsonBs.deserialize(json, String[].class));
    }

    @Test
    public void intTest() {
        int[] ints = new int[]{1,2,3};
        final String intJson = "[1,2,3]";
        Assert.assertEquals(intJson, JsonBs.serialize(ints));

        //[1, 2, 3]
        System.out.println(Arrays.toString(JsonBs.deserialize(intJson, int[].class)));
    }

    @Test
    public void integerTest() {
        Integer[] ints = new Integer[]{1,2,3};
        final String json = "[1,2,3]";
        Assert.assertEquals(json, JsonBs.serialize(ints));

        //[1, 2, 3]
        System.out.println(Arrays.toString(JsonBs.deserialize(json, Integer[].class)));
    }

}
