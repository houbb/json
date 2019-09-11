package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.lang.reflect.TypeUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.deserialize.DeserializeFactory;
import com.github.houbb.json.support.scanner.impl.JsonMapScanner;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 针对 Map 的反序列化
 *
 * @author binbin.hou
 * @since 0.0.6
 * @see CollectionDeserialize 针对这两个类的反射处理都是非常麻烦，甚至说结果不那么正确的处理。可以单独提供两个方法。
 */
public class MapDeserialize<K,V> implements IDeserialize<Map<K,V>> {

    @SuppressWarnings("unchecked")
    @Override
    public Map deserialize(String json, Class<Map<K, V>> mapClass) {
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return TypeUtil.createMap(mapClass);
        }
        // 获取类型信息
        Map map = TypeUtil.createMap(mapClass);

        // 首先是一个字符串的集合
        Pair<Type, Type> typeTypePair = TypeUtil.getMapKeyValueType(mapClass);
        final Class keyClass = (Class) typeTypePair.getValueOne();
        final Class valueClass = (Class) typeTypePair.getValueTwo();
        final IDeserialize keyDes = DeserializeFactory.getDeserialize(keyClass);
        final IDeserialize valueDes = DeserializeFactory.getDeserialize(valueClass);
        List<Pair<String, String>> collectionJsons = Instances.singleton(JsonMapScanner.class)
                .scan(trimJson);

        for(Pair<String, String> pair : collectionJsons) {
            final Object key = keyDes.deserialize(pair.getValueOne(), keyClass);
            final Object value = valueDes.deserialize(pair.getValueTwo(), valueClass);
            map.put(key, value);
        }
        return map;
    }

}
