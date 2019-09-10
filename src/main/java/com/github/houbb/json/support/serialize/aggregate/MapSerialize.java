package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonMapConst;

import java.util.List;
import java.util.Map;

/**
 * 针对 Map 的序列化
 *
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class MapSerialize<K,V> implements ISerialize<Map<K,V>> {

    @Override
    public String serialize(Map<K, V> map) {
        if(MapUtil.isEmpty(map)) {
            return JsonMapConst.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder(JsonMapConst.START);
        List<String> entryJsonList = Guavas.newArrayList(map.size());
        for(Map.Entry<K,V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();

            String entryJson = JsonBs.serialize(key)+JsonMapConst.SPLIT+JsonBs.serialize(value);
            entryJsonList.add(entryJson);
        }
        stringBuilder.append(CollectionUtil.join(entryJsonList, PunctuationConst.COMMA));
        stringBuilder.append(JsonMapConst.END);
        return stringBuilder.toString();
    }

}
