package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 测试
 * @author binbin.hou
 * @since 0.0.6
 */
public class MapTest {

    @Test
    public void stringTest() {
        Map<String, String> map = new HashMap<>();
        map.put("123", "456");

        System.out.println(JsonBs.serialize(map));
    }

    @Test
    public void integerTest() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(123, 456);

        System.out.println(JsonBs.serialize(map));
    }

}
