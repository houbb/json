package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.reflect.meta.field.IFieldMeta;
import com.github.houbb.heaven.reflect.meta.field.impl.FieldMetas;
import com.github.houbb.json.support.config.IDeserializeConfig;
import com.github.houbb.json.support.deserialize.handler.JsonInterfaceInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 对象反序列化
 * @author binbin.hou
 * @since 0.1.4
 * @param <T> 泛型
 */
public class InterfaceDeserialize<T> extends AbstractFieldMetaDeserialize<T> {

    @Override
    protected InvocationHandler createInvocationHandler() {
        return new JsonInterfaceInvocationHandler();
    }

    /**
     * 构建字段原始数据信息
     * @param tClass 类型
     * @return 结果信息列表
     * @since 0.1.4
     */
    @Override
    protected List<IFieldMeta> buildFieldMetaList(Class<T> tClass, IDeserializeConfig config) {
        return FieldMetas.buildMethodsMetaList(tClass);
    }

    @Override
    protected void processInstanceField(T instance, IFieldMeta fieldMeta, InvocationHandler invocationHandler, IDeserializeConfig config) {
        JsonInterfaceInvocationHandler handler = (JsonInterfaceInvocationHandler) invocationHandler;
        handler.setFieldValue(fieldMeta.getName(), fieldMeta.getValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T createInstance(Class<T> tClass,
                               final InvocationHandler invocationHandler) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return (T) Proxy.newProxyInstance(loader, new Class<?>[] { tClass }, invocationHandler);
    }

}
