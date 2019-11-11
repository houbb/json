package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.reflect.meta.field.IFieldMeta;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.TypeUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.support.context.IDeserializeContext;
import com.github.houbb.json.support.scanner.impl.JsonFieldMetaScanner;

import java.lang.reflect.InvocationHandler;
import java.util.List;

/**
 * 抽象字段元数据反序列化
 * @author binbin.hou
 * @see InterfaceDeserialize 接口反序列化
 * @since 0.1.4
 */
public abstract class AbstractFieldMetaDeserialize<T> implements IDeserialize<T> {

    /**
     * 创建实例信息
     * @param tClass 类信息
     * @param invocationHandler 处理类
     * @return 实例
     * @since 0.1.4
     */
    protected abstract T createInstance(final Class<T> tClass,
                                        final InvocationHandler invocationHandler);

    /**
     * 创建一个反射处理类
     * （1）主要用于代理使用
     * @return 代理实现
     * @since 0.1.4
     */
    protected InvocationHandler createInvocationHandler(){
        return null;
    }

    /**
     * 构建元字段信息列表
     * @param tClass 类信息
     * @return 元字段信息列表
     * @since 0.1.4
     */
    protected abstract List<IFieldMeta> buildFieldMetaList(final Class<T> tClass);

    /**
     * 开始处理实例的字段值
     * @param instance 实例
     * @param fieldMeta 字段元数据
     * @param invocationHandler 代理处理类
     * @since 0.1.4
     */
    protected abstract void processInstanceField(final T instance, final IFieldMeta fieldMeta,
                                                 final InvocationHandler invocationHandler);

    @Override
    public T deserialize(String json, Class<T> tClass,
                         final IDeserializeContext context) {
        //1. 基础对象创建
        InvocationHandler invocationHandler = this.createInvocationHandler();
        T instance = createInstance(tClass, invocationHandler);

        //2. 快速返回
        if(JsonBeanConst.NULL.equals(json)) {
            return instance;
        }

        //3. 构建基本字段信息
        final List<IFieldMeta> fieldList = buildFieldMetaList(tClass);
        //3.1 快速返回
        if(CollectionUtil.isEmpty(fieldList)) {
            return instance;
        }
        //3.2 构建结果
        List<Pair<String, String>> valueJsonList = new JsonFieldMetaScanner().scan(json);
        if(CollectionUtil.isEmpty(valueJsonList)) {
            return instance;
        }
        //3.3 循环设置属性值
        fillFieldMetaValue(fieldList, valueJsonList);

        //3.4 交给子类，自己实现对应的值处理
        for(IFieldMeta fieldMeta : fieldList) {
            // 如果字段为空，直接跳过即可
            final Object value = fieldMeta.getValue();
            if(ObjectUtil.isNull(value)) {
                continue;
            }

            this.processInstanceField(instance, fieldMeta, invocationHandler);
        }

        //4. 返回结果
        return instance;
    }

    /**
     * 填充对应的值信息
     * @param valueJsonList 列表信息
     * @param fieldMetaList 结果原始信息
     * @since 0.1.4
     */
    private void fillFieldMetaValue(final List<IFieldMeta> fieldMetaList,
                                    List<Pair<String, String>> valueJsonList) {
        for(IFieldMeta fieldMeta : fieldMetaList) {
            String fieldName = fieldMeta.getName();
            final String valueJson = getValueJson(fieldName, valueJsonList);
            if(StringUtil.isEmpty(valueJson)) {
                continue;
            }

            final Class fieldType = fieldMeta.getType();
            Object value;
            if(ClassTypeUtil.isCollection(fieldType)) {
                final Class componentType = fieldMeta.getComponentType();
                value = JsonBs.deserializeArray(valueJson, componentType);
            } else {
                value = JsonBs.deserialize(valueJson, fieldType);
            }

            // 统一设置类型转换值。
            final Object castValue = TypeUtil.cast(value, fieldType);
            fieldMeta.setValue(castValue);
        }
    }

    /**
     * 获取对应的 value json 信息
     * @param fieldName 字段名称
     * @param valueJsonList 字段值列表
     * @return 结果
     * @since 0.1.4
     */
    private String getValueJson(final String fieldName,
                                final List<Pair<String, String>> valueJsonList) {
        for(Pair<String, String> pair : valueJsonList) {
            if(pair.getValueOne().equals(fieldName)) {
                return pair.getValueTwo();
            }
        }
        return null;
    }

}
