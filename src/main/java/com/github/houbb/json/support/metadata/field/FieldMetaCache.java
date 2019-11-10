package com.github.houbb.json.support.metadata.field;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.support.metadata.field.impl.FieldMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字段原始信息缓存
 * @author binbin.hou
 * @since 0.1.6
 */
public final class FieldMetaCache {

    private FieldMetaCache(){}

    /**
     * 缓存信息
     */
    private static final Map<Class, List<IFieldMeta>> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取字段信息
     * @param tClass 类型
     * @return 结果列表
     * @since 0.1.6
     */
    public static List<IFieldMeta> getFieldMetaList(final Class tClass) {
        List<IFieldMeta> resultList = CACHE.get(tClass);

        if(ObjectUtil.isNotNull(resultList)) {
            return resultList;
        }

        // 默认
        List<Field> fieldList = ClassUtil.getModifyableFieldList(tClass);
        resultList = CollectionUtil.toList(fieldList, new IHandler<Field, IFieldMeta>() {
            @Override
            public IFieldMeta handle(Field field) {
                IFieldMeta fieldMeta = new FieldMeta();
                fieldMeta.setName(field.getName());
                fieldMeta.setType(field.getType());
                fieldMeta.setField(field);
                return fieldMeta;
            }
        });

        CACHE.put(tClass, resultList);
        return resultList;
    }

}
