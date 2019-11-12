package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.constant.MethodConst;
import com.github.houbb.heaven.reflect.meta.field.IFieldMeta;
import com.github.houbb.heaven.reflect.meta.field.impl.FieldMetas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectMethodUtil;
import com.github.houbb.json.support.config.IDeserializeConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
    protected List<IFieldMeta> buildFieldMetaList(Class<T> tClass, IDeserializeConfig config) {
        // 是否基于字段处理
        if(config.fieldBased()) {
            return FieldMetas.buildFieldsMetaList(tClass);
        } else {
            return FieldMetas.buildWriteMethodsMetaList(tClass);
        }
    }

    @Override
    protected void processInstanceField(T instance, IFieldMeta fieldMeta, InvocationHandler invocationHandler,
                                        IDeserializeConfig config) {
        // 基于字段处理
        if(config.fieldBased()) {
            ReflectFieldUtil.setValue(fieldMeta.getField(), instance, fieldMeta.getValue());
        } else {
            //基于 set 方法
            final Object fieldValue = fieldMeta.getValue();
            if(ObjectUtil.isNull(fieldValue)) {
                return;
            }
            // 执行设置属性值
            String setMethodName = MethodConst.SET_PREFIX + StringUtil.firstToUpperCase(fieldMeta.getName());
            final Class clazz = instance.getClass();
            Method method = ClassUtil.getMethod(clazz, setMethodName, fieldMeta.getType());
            ReflectMethodUtil.invoke(instance, method, fieldValue);
        }
    }

}
