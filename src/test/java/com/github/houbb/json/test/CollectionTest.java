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

        final String json = "[\"10\",\"20\",\"30\"]";
        Assert.assertEquals(json, JsonBs.serialize(strings));
        System.out.println(JsonBs.deserialize(json, List.class));
    }

}
