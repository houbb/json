package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 集合测试
 * @author binbin.hou
 * @since 0.0.5
 */
public class CollectionTest {

    @Test
    public void stringTest() {
        List<String> strings = new ArrayList<>();
        strings.add("10");
        strings.add("20");
        strings.add("30");

        Class clazz = strings.getClass();

        final String json = "[\"10\",\"20\",\"30\"]";
        Assert.assertEquals(json, JsonBs.serialize(strings));
        System.out.println(JsonBs.deserialize(json, clazz));
    }

    @Test
    public void intTest() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        final String json = "[1,2,3]";
        Assert.assertEquals(json, JsonBs.serialize(integers));
        System.out.println(JsonBs.deserialize(json, integers.getClass()));
    }

}
