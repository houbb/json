package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.reflect.ClassGenericUtil;
import com.github.houbb.heaven.util.lang.reflect.TypeUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.deserialize.DeserializeFactory;
import com.github.houbb.json.support.scanner.impl.JsonIterableScanner;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

/**
 * 针对集合的反序列化
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public class CollectionDeserialize<T> implements IDeserialize<Collection<T>> {

    /**
     * 获取元素的类型
     * TODO: 这里是一个难点 如果没有好办法，就直接使用 fastjson 的方式。
     * @param collectionClass 元素的类型
     * @return 结果
     */
    private Class getItemClass(final Class<Collection<T>> collectionClass) {
        return TypeUtil.getGenericType(collectionClass);
    }

    @Override
    public Collection<T> deserialize(String json, Class<Collection<T>> collectionClass) {
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return TypeUtil.createCollection(collectionClass);
        }

        // 获取对应的序列化接口，考虑是否使用快速模式
        final Class itemClass = getItemClass(collectionClass);
        IDeserialize deserialize = DeserializeFactory.getDeserialize(itemClass);
        List<String> stringList = Instances.singleton(JsonIterableScanner.class).scan(trimJson, deserialize);
        Collection collection = TypeUtil.createCollection(collectionClass, stringList.size());
        for(String itemJson : stringList) {
            T item = (T) deserialize.deserialize(itemJson, itemClass);
            collection.add(item);
        }
        return collection;
    }

}
