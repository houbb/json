/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.api.impl;

import com.github.houbb.json.api.JsonApi;
import com.github.houbb.json.utils.CollectionUtils;
import com.github.houbb.json.utils.ObjectUtils;
import com.github.houbb.json.utils.StringUtils;
import com.github.houbb.json.constant.JsonConstant;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.paradise.common.constant.CommonConstant;
import com.github.houbb.paradise.common.util.ArrayUtil;
import com.github.houbb.paradise.common.util.ClassUtil;
import com.github.houbb.paradise.common.util.CollectionUtil;
import com.github.houbb.paradise.common.util.ObjectUtil;
import com.github.houbb.paradise.common.util.StringUtil;
import com.github.houbb.paradise.common.util.reflection.ReflectionUtil;
import com.github.houbb.json.support.config.JsonConfig;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p> TODO: 驱动 log paradise 包的更新</p>
 *
 * <pre> Created: 2018/5/3 下午10:15  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonApiImpl implements JsonApi {

    @Override
    public String toJson(Object object) {
        if(ObjectUtil.isNull(object)) {
            return null;
        }
        //1. 这里不需要，直接调整即可
        if(ClassUtil.isPrimitive(object)) {
            return String.valueOf(object);
        }

        if(ObjectUtils.isArray(object)) {
            Object[] objects = (Object[]) object;
            StringBuilder stringBuilder = new StringBuilder(JsonConstant.JSON_ARRAY_PREFIX);
            List<String> stringList = new LinkedList<>();
            for(Object entry : objects) {
                stringList.add(getSingleObjectJson(entry));
            }
            stringBuilder.append(CollectionUtils.join(stringList, CommonConstant.COMMA));
            stringBuilder.append(JsonConstant.JSON_ARRAY_SUPPFIX);
            return stringBuilder.toString();
        }
        if(ObjectUtils.isList(object)) {
            List<Object> objectList = (List<Object>) object;
            StringBuilder stringBuilder = new StringBuilder(JsonConstant.JSON_ARRAY_PREFIX);
            List<String> stringList = new LinkedList<>();
            for(Object entry : objectList) {
                stringList.add(getSingleObjectJson(entry));
            }
            stringBuilder.append(CollectionUtils.join(stringList, CommonConstant.COMMA));
            stringBuilder.append(JsonConstant.JSON_ARRAY_SUPPFIX);
            return stringBuilder.toString();
        }
        if(ObjectUtils.isMap(object)) {
            //todo...这里对于各种数据结构的处理 明显过于笨拙
        }

        return getSingleObjectJson(object);
    }

    /**
     * 获取单独的对象 json
     * @return
     */
    private String getSingleObjectJson(Object object) {
        List<Field> fieldList = ReflectionUtil.getAllFieldsList(object.getClass());
        StringBuilder stringBuilder = new StringBuilder(JsonConstant.JSON_OBJECT_PREFIX);
        for(Field field : fieldList) {
            field.setAccessible(true);
            final String name = field.getName();
            Class<?> type = field.getType();
            try {
                final Object value = field.get(object);
                final String actualName = StringUtils.bothAppendDoubleQuote(name);

                final String actualValue = getFieldValue(type, value);
                stringBuilder.append(actualName)
                        .append(CommonConstant.COLON)
                        .append(actualValue)
                        .append(CommonConstant.COMMA);
            } catch (IllegalAccessException e) {
                throw new JsonRuntimeException(e);
            }
        }
        //删除最后一个,
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length()-1));
        stringBuilder.append(JsonConstant.JSON_OBJECT_SUPPFIX);
        return stringBuilder.toString();
    }

    private String getFieldValue(Class<?> type, Object value) {
        if(ObjectUtil.isNull(value)) {
            return null;
        }
        if(String.class.equals(type)) {
            return StringUtils.bothAppendDoubleQuote(value.toString());
        }
        if(Date.class.equals(type)) {
            Date date = (Date) value;
            return String.valueOf(date.getTime());
        }

        return StringUtils.castToString(value);
    }

    @Override
    public String toJson(Object object, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public <T> T parseObject(String json, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> List<T> parseArray(String json, Class<T> tClass) {
        return null;
    }

}
