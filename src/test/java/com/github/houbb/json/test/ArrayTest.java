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
    public void emptyTest() {
        String[] strings = new String[]{};

        Assert.assertEquals("[]", JsonBs.serialize(strings));
        System.out.println(Arrays.toString(JsonBs.deserialize("[]", String[].class)));
    }

    @Test
    public void stringTest() {
        String[] strings = new String[]{"a", "b", "c"};
        final String json = "[\"a\",\"b\",\"c\"]";
        Assert.assertEquals(json, JsonBs.serialize(strings));
        System.out.println(Arrays.toString(JsonBs.deserialize(json, String[].class)));
    }

    @Test
    public void intTest() {
        int[] ints = new int[]{1,2,3};
        final String json = "[1,2,3]";
        final String empty = "[]";
        Assert.assertEquals(json, JsonBs.serialize(ints));
        System.out.println(Arrays.toString(JsonBs.deserialize(empty, int[].class)));
        System.out.println(Arrays.toString(JsonBs.deserialize(json, int[].class)));
    }

    @Test
    public void integerTest() {
        Integer[] ints = new Integer[]{1,2,3};
        final String json = "[1,2,3]";
        Assert.assertEquals(json, JsonBs.serialize(ints));
        System.out.println(Arrays.toString(JsonBs.deserialize(json, Integer[].class)));
    }

}
