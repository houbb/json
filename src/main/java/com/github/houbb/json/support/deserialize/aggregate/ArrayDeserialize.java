package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.deserialize.DeserializeFactory;
import com.github.houbb.json.support.scanner.impl.JsonIterableScanner;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 针对数组的反序列化
 * （1）获取数组单个元素的信息
 * （2）使用 [] 作为开始和结尾，元素之间用 COMMA 分隔开。
 *
 * 核心问题：
 * 如何解决 , 这种符号，如果出现在了字符串中怎么处理？
 *
 * （1）直接根据 "开始进行开始模式，直到遇到另外一个 " 则认为结束。
 * 在两次匹配中间出现的 COMMA 直接进行忽略，数字等信息是不会出现逗号的。
 *
 *  关于 " 的转义问题，转义后卫：
 *
 * （2）拓展性
 * 如何保证系统和用户自定义的信息，都可以自动识别。
 * 可以考虑使用接口。此处不再引入注解，反射会比较影响性能。
 *
 * @author binbin.hou
 * @since 0.0.4
 * @param <T> 数组元素的泛型
 */
public class ArrayDeserialize<T> implements IDeserialize<T[]> {

    @Override
    @SuppressWarnings("unchecked")
    public T[] deserialize(String json, Class<T[]> aClass) {
        Class<T> itemClass = (Class<T>) aClass.getComponentType();
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return (T[]) Array.newInstance(itemClass, 0);
        }

        // 获取对应的序列化接口，考虑是否使用快速模式
        IDeserialize deserialize = DeserializeFactory.getDeserialize(itemClass);
        // 开始和结尾要去掉。
        List<String> stringList = Instances.singleton(JsonIterableScanner.class).scan(trimJson);
        final int arraySize = stringList.size();
        T[] resultArray = (T[]) Array.newInstance(itemClass, arraySize);
        for(int i = 0; i < arraySize; i++) {
            String itemJson = stringList.get(i);
            T item = (T) deserialize.deserialize(itemJson, itemClass);
            Array.set(resultArray, i, item);
        }
        return resultArray;
    }


}
