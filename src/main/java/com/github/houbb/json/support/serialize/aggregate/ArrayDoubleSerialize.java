package com.github.houbb.json.support.serialize.aggregate;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.context.ISerializeContext;
import com.github.houbb.json.util.JsonIterableUtil;

import java.util.List;

/**
 * 数组 double 类型序列化
 * @author binbin.hou
 * @since 0.0.4
 */
@ThreadSafe
public class ArrayDoubleSerialize implements ISerialize<double[]> {

    @Override
    public String serialize(double[] objects, ISerializeContext context) {
        if(ArrayPrimitiveUtil.isEmpty(objects)) {
            return JsonIterableConst.EMPTY;
        }

        List<String> stringList = ArrayPrimitiveUtil.toList(objects, new IHandler<Double, String>() {
            @Override
            public String handle(Double object) {
                return JsonBs.serialize(object);
            }
        });
        return JsonIterableUtil.buildArrayJson(stringList);
    }

}
