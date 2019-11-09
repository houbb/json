package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.model.DefaultBeanDefinition;
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

    /**
     * 数组转换测试
     * @since 0.1.2
     */
    @Test
    public void parseArrayTest() {
        final String json = "[{\"name\":\"apple\",\"className\":\"com.github.houbb.ioc.test.service.Apple\"}]";
        System.out.println(JsonBs.deserializeArray(json, DefaultBeanDefinition.class));

        final String jsonJsk = "[1,2,3]";
        System.out.println(JsonBs.deserializeArray(jsonJsk, Integer.class));
    }

    /**
     * 数组转换测试-换行
     * @since 0.1.2
     */
    @Test
    public void parseArrayReturnTest() {
        final String json = "[\n" +
                "{\"name\":\"apple\",\"className\":\"com.github.houbb.ioc.test.service.Apple\"}\n" +
                "]";
        System.out.println(JsonBs.deserializeArray(json, DefaultBeanDefinition.class));
    }

    @Test
    public void parseMultiArrayTest() {
        final String json = "[\n" +
                "{\"name\":\"apple\",\"className\":\"com.github.houbb.ioc.test.service.Apple\"},\n" +
                "{\"name\":\"apple2\",\"className\":\"com.github.houbb.ioc.test.service.Apple\"}\n" +
                "]";

        System.out.println(JsonBs.deserializeArray(json, DefaultBeanDefinition.class));
    }


    /**
     * 数组中嵌套对象，对象属性中又包含数组。
     *
     * 建议使用类似【逆波兰计算器】的方式
     * 通过堆栈处理相关信息，不然直接处理，会出现问题。
     *
     * 【注意】
     * 1. 处理符号时，如果是被转义的符号不要算在下面的处理中。
     * 2. 所有的空格，换行符等信息，如果不是字符串信息，需要进行过滤筛选。
     *
     * 这个无法通过正则直接过滤，需要从左到右顺序执行，方能知道符号是否为字符串内容，还是多余空格。
     *
     * 【处理核心流程】
     * （1）对象开始为左花括号，一直到对应的右花括号，才算这个对象 json 匹配完成
     * （2）数组开始为在方括号，一直到对应的右方括号，才算这个数组 json 匹配完成
     *
     *  直接获取对应的下标信息。
     *
     * @since 0.1.4
     */
    @Test
    public void arrayObjectArrayTest() {

    }

}
