package com.github.houbb.json.util;

import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.*;

/**
 * <p> project: json-TypeUtils </p>
 * <p> create on 2019/11/9 22:32 </p>
 *
 * @author Administrator
 * @since 1.0.0
 */
public class TypeUtils {

    /**
     * 转换为指定的数据类型
     * @param obj 原始数据
     * @param type 预期类型
     * @param <T> 结果泛型
     * @return 结果
     * @since 0.1.4
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj, Type type){
        if(obj == null){
            return null;
        }
        if(type instanceof ParameterizedType){
            return (T) cast(obj, (ParameterizedType) type);
        }
        if(obj instanceof String){
            String strVal = (String) obj;
            if(strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)){
                return null;
            }

            return (T) strVal;
        }

        // 其他情况，直接类型强转。
        // 后期可以丰富这里的特性。
        return (T) obj;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> T cast(Object obj, ParameterizedType type){
        Type rawTye = type.getRawType();

        if(rawTye == List.class || rawTye == ArrayList.class){
            Type itemType = type.getActualTypeArguments()[0];
            if(obj instanceof List){
                List listObj = (List) obj;
                List arrayList = new ArrayList(listObj.size());

                for (Object item : listObj) {
                    Object itemValue;
                    if (itemType instanceof Class) {
                        itemValue = cast(item, (Class<T>) itemType);
                    } else {
                        itemValue = cast(item, itemType);
                    }

                    arrayList.add(itemValue);
                }
                return (T) arrayList;
            }
        }

        if(rawTye == Set.class || rawTye == HashSet.class //
                || rawTye == TreeSet.class //
                || rawTye == Collection.class //
                || rawTye == List.class //
                || rawTye == ArrayList.class){
            Type itemType = type.getActualTypeArguments()[0];
            if(obj instanceof Iterable){
                Collection collection;
                if(rawTye == Set.class || rawTye == HashSet.class){
                    collection = new HashSet();
                } else if(rawTye == TreeSet.class){
                    collection = new TreeSet();
                } else{
                    collection = new ArrayList();
                }
                for (Object item : (Iterable) obj) {
                    Object itemValue;
                    if (itemType instanceof Class) {
                        itemValue = cast(item, (Class<T>) itemType);
                    } else {
                        itemValue = cast(item, itemType);
                    }

                    collection.add(itemValue);
                }
                return (T) collection;
            }
        }

        if(rawTye == Map.class || rawTye == HashMap.class){
            Type keyType = type.getActualTypeArguments()[0];
            Type valueType = type.getActualTypeArguments()[1];
            if(obj instanceof Map){
                Map map = new HashMap();
                for(Map.Entry entry : ((Map<?,?>) obj).entrySet()){
                    Object key = cast(entry.getKey(), keyType);
                    Object value = cast(entry.getValue(), valueType);
                    map.put(key, value);
                }
                return (T) map;
            }
        }
        if(obj instanceof String){
            String strVal = (String) obj;
            if(strVal.length() == 0){
                return null;
            }
        }
        if(type.getActualTypeArguments().length == 1){
            Type argType = type.getActualTypeArguments()[0];
            if(argType instanceof WildcardType){
                return (T) cast(obj, rawTye);
            }
        }

        if (rawTye == Map.Entry.class && obj instanceof Map && ((Map) obj).size() == 1) {
            Map.Entry entry = (Map.Entry) ((Map) obj).entrySet().iterator().next();
            return (T) entry;
        }

        throw new JsonRuntimeException("无法转换为类型： " + type, JsonRespCode.DES_CAN_NOT_CAST_TO_TYPE);
    }
}
