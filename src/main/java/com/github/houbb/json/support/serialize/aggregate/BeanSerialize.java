package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectMethodUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.constant.JsonConst;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.metadata.field.IFieldMeta;
import com.github.houbb.json.support.metadata.field.impl.FieldMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 针对 Bean 的序列化
 *
 * @author binbin.hou
 * @since 0.0.7
 */
@ThreadSafe
public class BeanSerialize implements ISerialize {

    @Override
    public String serialize(Object o) {
        List<IFieldMeta> fieldList = buildFieldList(o);
        if(CollectionUtil.isEmpty(fieldList)) {
            return JsonBeanConst.NULL;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JsonBeanConst.START);
        List<String> fieldStrings = Guavas.newArrayList();

        for(IFieldMeta field : fieldList) {
           final Object object = field.getValue();
           if(ObjectUtil.isNull(object)) {
               continue;
           }
           final String fieldName = field.getName();
           final String fieldJson = JsonBs.serialize(fieldName);
           final String valueJson = JsonBs.serialize(object);
           fieldStrings.add(fieldJson+PunctuationConst.COLON+valueJson);
        }

        if(CollectionUtil.isNotEmpty(fieldStrings)) {
            stringBuilder.append(CollectionUtil.join(fieldStrings, PunctuationConst.COMMA));
        }

        stringBuilder.append(JsonBeanConst.END);
        return stringBuilder.toString();
    }

    /**
     * 构建字段列表信息
     * @param instance 实例信息
     * @return 结果列表
     * @since 0.1.4
     */
    private List<IFieldMeta> buildFieldList(final Object instance) {
        final Class tClass = instance.getClass();

        // 代理类
        if(tClass.getName().startsWith(JsonConst.PROXY_CLASS_NAME)) {
            Method[] methods = tClass.getDeclaredMethods();
            if(ArrayUtil.isEmpty(methods)) {
                return Guavas.newArrayList();
            }

            List<IFieldMeta> fieldMetaList = Guavas.newArrayList();
            for(Method method : methods) {
                final String methodName = method.getName();
                method.setAccessible(true);
                if(method.getName().startsWith("get")) {
                    final Object value = ReflectMethodUtil.invoke(instance, method, new Object[]{});
                    IFieldMeta fieldMeta = new FieldMeta();
                    fieldMeta.setName(StringUtil.firstToLowerCase(methodName.substring(3)));
                    fieldMeta.setType(method.getReturnType());
                    fieldMeta.setValue(value);

                    fieldMetaList.add(fieldMeta);
                }
            }
            return fieldMetaList;
        }

        // 默认
        List<Field> fieldList = ClassUtil.getModifyableFieldList(tClass);
        return CollectionUtil.toList(fieldList, new IHandler<Field, IFieldMeta>() {
            @Override
            public IFieldMeta handle(Field field) {
                IFieldMeta fieldMeta = new FieldMeta();
                fieldMeta.setName(field.getName());
                fieldMeta.setType(field.getType());
                fieldMeta.setValue(ReflectFieldUtil.getValue(field, instance));
                return fieldMeta;
            }
        });
    }

}
