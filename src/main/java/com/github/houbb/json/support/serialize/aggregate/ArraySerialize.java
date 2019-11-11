package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.context.ISerializeContext;
import com.github.houbb.json.util.JsonIterableUtil;

import java.util.List;

/**
 * 针对数组的序列化
 * （1）获取数组单个元素的信息
 * （2）使用 [] 作为开始和结尾，元素之间用 COMMA 分隔开。
 * （3）需要兼容基本类型
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class ArraySerialize implements ISerialize<Object> {

    @Override
    public String serialize(Object object, ISerializeContext context) {
        final Class clazz = object.getClass();

        //8 大基本类型判断
        if(boolean[].class.equals(clazz)) {
            boolean[] objects = (boolean[])object;
            return Instances.singleton(ArrayBooleanSerialize.class).serialize(objects, context);
        } else if(byte[].class.equals(clazz)) {
            byte[] objects = (byte[])object;
            return Instances.singleton(ArrayByteSerialize.class).serialize(objects, context);
        } else if(char[].class.equals(clazz)) {
            char[] objects = (char[])object;
            return Instances.singleton(ArrayCharSerialize.class).serialize(objects, context);
        } else if(short[].class.equals(clazz)) {
            short[] objects = (short[])object;
            return Instances.singleton(ArrayShortSerialize.class).serialize(objects, context);
        } else if(int[].class.equals(clazz)) {
            int[] objects = (int[])object;
            return Instances.singleton(ArrayIntSerialize.class).serialize(objects, context);
        } else if(float[].class.equals(clazz)) {
            float[] objects = (float[])object;
            return Instances.singleton(ArrayFloatSerialize.class).serialize(objects, context);
        } else if(double[].class.equals(clazz)) {
            double[] objects = (double[])object;
            return Instances.singleton(ArrayDoubleSerialize.class).serialize(objects, context);
        } else if(long[].class.equals(clazz)) {
            long[] objects = (long[])object;
            return Instances.singleton(ArrayLongSerialize.class).serialize(objects, context);
        }

        // 对象数组类型
        Object[] objects = (Object[])object;
        if(ArrayUtil.isEmpty(objects)) {
            return JsonIterableConst.EMPTY;
        }

        List<String> stringList = ArrayUtil.toList(objects, new IHandler<Object, String>() {
            @Override
            public String handle(Object object) {
                return JsonBs.serialize(object);
            }
        });
        return JsonIterableUtil.buildArrayJson(stringList);
    }

}
