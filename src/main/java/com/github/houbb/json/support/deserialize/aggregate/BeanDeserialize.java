package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.support.metadata.field.IFieldMeta;
import com.github.houbb.json.support.metadata.field.impl.FieldMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.util.List;

/**
 * 对象反序列化
 * @author binbin.hou
 * @since 0.1.4
 * @param <T> 泛型
 */
public class BeanDeserialize<T> extends AbstractFieldMetaDeserialize<T> {

    @Override
    protected T createInstance(Class<T> tClass, InvocationHandler invocationHandler) {
        return ClassUtil.newInstance(tClass);
    }

    @Override
    protected List<IFieldMeta> buildFieldMetaList(Class<T> tClass) {
        List<Field> fieldList = ClassUtil.getModifyableFieldList(tClass);

        return CollectionUtil.toList(fieldList, new IHandler<Field, IFieldMeta>() {
            @Override
            public IFieldMeta handle(Field field) {
                IFieldMeta fieldMeta = new FieldMeta();
                fieldMeta.setField(field);
                fieldMeta.setName(field.getName());
                fieldMeta.setType(field.getType());
                fieldMeta.setComponentType(ReflectFieldUtil.getComponentType(field));
                return fieldMeta;
            }
        });
    }

    @Override
    protected void processInstanceField(T instance, IFieldMeta fieldMeta, InvocationHandler invocationHandler) {
        ReflectFieldUtil.setValue(fieldMeta.getField(), instance, fieldMeta.getValue());
    }

}
