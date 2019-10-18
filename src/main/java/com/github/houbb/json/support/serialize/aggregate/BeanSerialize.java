package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;

import java.lang.reflect.Field;
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
        List<Field> fieldList = ClassUtil.getModifyableFieldList(o.getClass());
        if(CollectionUtil.isEmpty(fieldList)) {
            return JsonBeanConst.NULL;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JsonBeanConst.START);
        List<String> fieldStrings = Guavas.newArrayList();

        try {
            for(Field field : fieldList) {
               final Object object = field.get(o);
               if(ObjectUtil.isNull(object)) {
                   continue;
               }
               final String fieldName = field.getName();
               final String fieldJson = JsonBs.serialize(fieldName);
               final String valueJson = JsonBs.serialize(object);
               fieldStrings.add(fieldJson+PunctuationConst.COLON+valueJson);
            }
        } catch (IllegalAccessException e) {
            throw new JsonRuntimeException(JsonRespCode.SER_ILLEGAL_ACCESS);
        }
        if(CollectionUtil.isNotEmpty(fieldStrings)) {
            stringBuilder.append(CollectionUtil.join(fieldStrings, PunctuationConst.COMMA));
        }

        stringBuilder.append(JsonBeanConst.END);
        return stringBuilder.toString();
    }

}
