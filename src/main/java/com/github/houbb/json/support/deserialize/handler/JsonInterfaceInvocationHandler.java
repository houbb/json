package com.github.houbb.json.support.deserialize.handler;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.TypeUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.json.bs.JsonBs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p> project: json-JsonBeanHandler </p>
 * <p> create on 2019/11/9 22:20 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
public class JsonInterfaceInvocationHandler implements InvocationHandler {

    /**
     * 存放内部属性
     * key: fieldName
     * value: 对应的值
     * @since 0.1.4
     */
    private final Map<String, Object> map;

    public JsonInterfaceInvocationHandler() {
        this.map = new HashMap<>();
    }

    /**
     * 设置字段值
     * @param fieldName 字段名称
     * @param fieldValue 字段值
     * @since 0.1.4
     */
    public synchronized void setFieldValue(final String fieldName, final Object fieldValue) {
        map.put(fieldName, fieldValue);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String methodName = method.getName();

        //getter
        //is
        if(ArrayUtil.isEmpty(args)) {
            //1.toString()
            if("toString".equals(methodName)) {
                return this.toString();
            }
            //2. hash
            if("hashCode".equals(methodName)) {
                return this.hashCode();
            }
            //3. 方法返回类型
            if("getReturnType".equals(methodName)) {
                return method.getReturnType();
            }
            if("getGenericReturnType".equals(methodName)) {
                return method.getGenericReturnType();
            }
            //4.. getter/is
            String fieldName = getFieldName(methodName);
            Object fieldValue = this.map.get(fieldName);
            final Class returnType = method.getReturnType();
            return TypeUtil.cast(fieldValue, returnType);
        } else if(1 == args.length) {
            // setter
            final Object param = args[0];

            //1. equals
            if("equals".equals(methodName)) {
                return this.equals(param);
            }

            //2. 设置属性
            String fieldName = getFieldName(methodName);
            this.setFieldValue(fieldName, param);
            return null;
        } else {
            throw new UnsupportedOperationException("Not support for call method " + method.getName());
        }

    }


    /**
     * 获取字段名称
     * @param methodName 方法名称
     * @return 结果
     * @since 0.1.4
     */
    private String getFieldName(final String methodName) {
        final int length = methodName.length();
        int startIndex = 0;
        if(methodName.startsWith("set")
            || methodName.startsWith("get")) {
            startIndex = 3;
        } else if(methodName.startsWith("is")) {
            startIndex = 2;
        } else {
            throw new UnsupportedOperationException("Not support for call method " + methodName);
        }

        return StringUtil.firstToLowerCase(methodName.substring(startIndex, length));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonInterfaceInvocationHandler that = (JsonInterfaceInvocationHandler) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return JsonBs.serialize(this.map);
    }

}
