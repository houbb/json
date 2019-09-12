package com.github.houbb.json.support.serialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.json.api.ISerialize;
import com.github.houbb.json.support.serialize.aggregate.ArraySerialize;
import com.github.houbb.json.support.serialize.aggregate.BeanSerialize;
import com.github.houbb.json.support.serialize.aggregate.CollectionSerialize;
import com.github.houbb.json.support.serialize.aggregate.MapSerialize;
import com.github.houbb.json.support.serialize.math.BigDecimalSerialize;
import com.github.houbb.json.support.serialize.math.BigIntegerSerialize;
import com.github.houbb.json.support.serialize.util.CurrencySerialize;
import com.github.houbb.json.support.serialize.util.DateSerialize;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 序列化工厂
 * @author binbin.hou
 * @since 0.0.1
 */
public final class SerializeFactory {

    private SerializeFactory(){}

    /**
     * 类与实例的映射关系
     * @since 0.0.2
     */
    private static final Map<Class, ISerialize> CLASS_INSTANCE_MAP = new HashMap<>();

    static {
        CLASS_INSTANCE_MAP.put(String.class, Instances.singleton(StringSerialize.class));
        CLASS_INSTANCE_MAP.put(Enum.class, Instances.singleton(EnumSerialize.class));

        CLASS_INSTANCE_MAP.put(Boolean.class, Instances.singleton(BooleanSerialize.class));
        CLASS_INSTANCE_MAP.put(Character.class, Instances.singleton(CharacterSerialize.class));
        CLASS_INSTANCE_MAP.put(Byte.class, Instances.singleton(ByteSerialize.class));
        CLASS_INSTANCE_MAP.put(Short.class, Instances.singleton(ShortSerialize.class));
        CLASS_INSTANCE_MAP.put(Integer.class, Instances.singleton(IntegerSerialize.class));
        CLASS_INSTANCE_MAP.put(Long.class, Instances.singleton(LongSerialize.class));
        CLASS_INSTANCE_MAP.put(Float.class, Instances.singleton(FloatSerialize.class));
        CLASS_INSTANCE_MAP.put(Double.class, Instances.singleton(DoubleSerialize.class));

        CLASS_INSTANCE_MAP.put(boolean.class, Instances.singleton(BooleanSerialize.class));
        CLASS_INSTANCE_MAP.put(char.class, Instances.singleton(CharacterSerialize.class));
        CLASS_INSTANCE_MAP.put(byte.class, Instances.singleton(ByteSerialize.class));
        CLASS_INSTANCE_MAP.put(short.class, Instances.singleton(ShortSerialize.class));
        CLASS_INSTANCE_MAP.put(int.class, Instances.singleton(IntegerSerialize.class));
        CLASS_INSTANCE_MAP.put(long.class, Instances.singleton(LongSerialize.class));
        CLASS_INSTANCE_MAP.put(float.class, Instances.singleton(FloatSerialize.class));
        CLASS_INSTANCE_MAP.put(double.class, Instances.singleton(DoubleSerialize.class));

        CLASS_INSTANCE_MAP.put(BigDecimal.class, Instances.singleton(BigDecimalSerialize.class));
        CLASS_INSTANCE_MAP.put(BigInteger.class, Instances.singleton(BigIntegerSerialize.class));

        CLASS_INSTANCE_MAP.put(Currency.class, Instances.singleton(CurrencySerialize.class));
        CLASS_INSTANCE_MAP.put(Date.class, Instances.singleton(DateSerialize.class));
    }

    /**
     * 获取序列化实现
     * （1）null
     * （2）8大基本类型
     * （3）对象
     * （4）集合/数组/map
     * @param object  对象
     * @return 对应实现
     */
    public static ISerialize getSerialize(final Object object) {
        if(ObjectUtil.isNull(object)) {
            return Instances.singleton(NullSerialize.class);
        }
        // 基本类型
        final Class clazz = object.getClass();
        ISerialize serialize = CLASS_INSTANCE_MAP.get(clazz);
        if(ObjectUtil.isNotNull(serialize)) {
            return serialize;
        }

        // 聚合类型
        // 数组
        if(ClassTypeUtil.isArray(clazz)) {
            return Instances.singleton(ArraySerialize.class);
        }
        // 集合
        if(ClassTypeUtil.isCollection(clazz)) {
            return Instances.singleton(CollectionSerialize.class);
        }
        // map
        if(ClassTypeUtil.isMap(clazz)) {
            return Instances.singleton(MapSerialize.class);
        }

        //bean
        return Instances.singleton(BeanSerialize.class);
    }



}
