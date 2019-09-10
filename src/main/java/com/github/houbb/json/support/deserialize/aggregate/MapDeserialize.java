package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.reflect.TypeUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.deserialize.DeserializeFactory;
import com.github.houbb.json.support.scanner.impl.JsonIterableScanner;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 针对 Map 的反序列化
 *
 * @author binbin.hou
 * @since 0.0.6
 */
public class MapDeserialize<K,V> implements IDeserialize<Map<K,V>> {

    @Override
    public Map deserialize(String json, Class<Map<K, V>> mapClass) {
        final String trimJson = json.trim();
        if(JsonIterableConst.EMPTY.equals(trimJson)) {
            return TypeUtil.createMap(mapClass);
        }
        // 获取类型信息
        Map map = TypeUtil.createMap(mapClass);

        // 首先是一个字符串的集合
//        List<String> collectionJsons = Instances.singleton(JsonIterableScanner.class).scan(trimJson, deserialize);
        return map;
    }

}
