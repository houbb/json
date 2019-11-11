package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.reflect.meta.field.IFieldMeta;
import com.github.houbb.heaven.reflect.meta.field.impl.FieldMetas;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

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
        return FieldMetas.buildFieldsMetaList(tClass);
    }

    @Override
    protected void processInstanceField(T instance, IFieldMeta fieldMeta, InvocationHandler invocationHandler) {
        ReflectFieldUtil.setValue(fieldMeta.getField(), instance, fieldMeta.getValue());
    }

}
