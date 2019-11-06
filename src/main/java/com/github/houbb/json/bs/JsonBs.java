package com.github.houbb.json.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.core.DefaultJson;
import com.github.houbb.json.support.scanner.impl.JsonArrayObjectScanner;
import com.github.houbb.json.support.scanner.impl.JsonIterableScanner;

import java.util.List;

/**
 * Json 引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class JsonBs {

    private JsonBs(){}

    /**
     * 序列化对象
     * @param object 对象
     * @return 结果
     * @since 0.0.1
     */
    public static String serialize(Object object) {
        return Instances.threadLocal(DefaultJson.class).serialize(object);
    }

    /**
     * 序列化对象为字节
     * @param object 对象
     * @return 结果
     * @since 0.1.1
     */
    public static byte[] serializeBytes(Object object) {
        String json = serialize(object);
        return StringUtil.stringToBytes(json);
    }

    /**
     * 反序列化对象
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 反序列化后的对象
     * @since 0.0.1
     */
    public static <T> T deserialize(String json, Class<T> tClass) {
        return Instances.threadLocal(DefaultJson.class).deserialize(json, tClass);
    }

    /**
     * 反序列化数组对象
     * @param json json
     * @param tClass 类信息
     * @param <T> 泛型
     * @return 反序列化后的对象列表
     * @since 0.1.2
     */
    public static <T> List<T> deserializeArray(String json, Class<T> tClass) {
        if(StringUtil.isEmpty(json)) {
            return null;
        }

        // 空列表
        List<T> resultList = Guavas.newArrayList();
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return resultList;
        }

        //非对象的常见 JDK 类型
        List<String> stringList;
        if(ClassTypeUtil.isJdk(tClass)) {
            stringList = Instances.singleton(JsonIterableScanner.class).scan(trimJson);
        } else {
            // 对象类型循环处理
            stringList = Instances.singleton(JsonArrayObjectScanner.class).scan(trimJson);
        }
        for(String entryJson : stringList) {
            String trim = StringUtil.trim(entryJson);
            T t = deserialize(trim, tClass);
            resultList.add(t);
        }

        return resultList;
    }

    /**
     * 反序列化字节数组
     * @param bytes 字节流
     * @param tClass 类型
     * @param <T> 泛型
     * @return 对象
     * @since 0.1.1
     */
    public static <T> T deserializeBytes(final byte[] bytes, Class<T> tClass) {
        String json = StringUtil.bytesToString(bytes);
        return deserialize(json, tClass);
    }

}
