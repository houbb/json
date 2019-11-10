package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
public class HelloWorldTest {

    /**
     * 序列化测试
     * @since 0.1.5
     */
    @Test
    public void serializeTest() {
        int[] ints = new int[]{1,2,3};
        String json = JsonBs.serialize(ints);
        Assert.assertEquals("[1,2,3]", json);
    }

    /**
     * 反序列化测试
     * @since 0.1.5
     */
    @Test
    public void deserializeTest() {
        final String json = "[1,2,3]";
        int[] ints = new int[]{1,2,3};
        Assert.assertArrayEquals(ints, JsonBs.deserialize(json, int[].class));
    }

    /**
     * 反序列化为列表测试
     * @since 0.1.5
     */
    @Test
    public void deserializeArrayTest() {
        final String json = "[1,2,3]";
        List<Integer> integerList = JsonBs.deserializeArray(json, Integer.class);
        Assert.assertEquals(3, integerList.size());
    }

}
