package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.context.ISerializeContext;
import com.github.houbb.json.util.JsonIterableUtil;

import java.util.Collection;
import java.util.List;

/**
 * 针对集合的序列化
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class CollectionSerialize implements ISerialize<Object> {

    @Override
    public String serialize(Object object, ISerializeContext context) {
        Collection<?> collection = (Collection)object;
        if(CollectionUtil.isEmpty(collection)) {
            return JsonIterableConst.EMPTY;
        }

        List<String> stringList = CollectionUtil.toList(collection, new IHandler<Object, String>() {
            @Override
            public String handle(Object object) {
                return JsonBs.serialize(object);
            }
        });
        return JsonIterableUtil.buildArrayJson(stringList);
    }

}
