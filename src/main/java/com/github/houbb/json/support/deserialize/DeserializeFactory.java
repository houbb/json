package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonConst;
import com.github.houbb.json.support.deserialize.aggregate.*;
import com.github.houbb.json.support.deserialize.math.BigDecimalDeserialize;
import com.github.houbb.json.support.deserialize.math.BigIntegerDeserialize;
import com.github.houbb.json.support.deserialize.util.CurrencyDeserialize;
import com.github.houbb.json.support.deserialize.util.DateDeserialize;
import com.github.houbb.json.support.serialize.EnumSerialize;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 反序列化工厂类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class DeserializeFactory {

    /**
     * @since 0.0.2
     */
    private DeserializeFactory(){}

    /**
     * 类实例映射
     * @since 0.0.2
     */
    private static final Map<Class, IDeserialize> CLASS_INSTANCE_MAP = new IdentityHashMap<>();

    static {
        CLASS_INSTANCE_MAP.put(String.class, Instances.singleton(StringDeserialize.class));
        CLASS_INSTANCE_MAP.put(Enum.class, Instances.singleton(EnumDeserialize.class));

        CLASS_INSTANCE_MAP.put(Boolean.class, Instances.singleton(BooleanDeserialize.class));
        CLASS_INSTANCE_MAP.put(Character.class, Instances.singleton(CharacterDeserialize.class));
        CLASS_INSTANCE_MAP.put(Byte.class, Instances.singleton(ByteDeserialize.class));
        CLASS_INSTANCE_MAP.put(Short.class, Instances.singleton(ShortDeserialize.class));
        CLASS_INSTANCE_MAP.put(Integer.class, Instances.singleton(IntegerDeserialize.class));
        CLASS_INSTANCE_MAP.put(Long.class, Instances.singleton(LongDeserialize.class));
        CLASS_INSTANCE_MAP.put(Float.class, Instances.singleton(FloatDeserialize.class));
        CLASS_INSTANCE_MAP.put(Double.class, Instances.singleton(DoubleDeserialize.class));

        CLASS_INSTANCE_MAP.put(boolean.class, Instances.singleton(BooleanDeserialize.class));
        CLASS_INSTANCE_MAP.put(char.class, Instances.singleton(CharacterDeserialize.class));
        CLASS_INSTANCE_MAP.put(byte.class, Instances.singleton(ByteDeserialize.class));
        CLASS_INSTANCE_MAP.put(short.class, Instances.singleton(ShortDeserialize.class));
        CLASS_INSTANCE_MAP.put(int.class, Instances.singleton(IntegerDeserialize.class));
        CLASS_INSTANCE_MAP.put(long.class, Instances.singleton(LongDeserialize.class));
        CLASS_INSTANCE_MAP.put(float.class, Instances.singleton(FloatDeserialize.class));
        CLASS_INSTANCE_MAP.put(double.class, Instances.singleton(DoubleDeserialize.class));

        CLASS_INSTANCE_MAP.put(BigDecimal.class, Instances.singleton(BigDecimalDeserialize.class));
        CLASS_INSTANCE_MAP.put(BigInteger.class, Instances.singleton(BigIntegerDeserialize.class));

        CLASS_INSTANCE_MAP.put(Currency.class, Instances.singleton(CurrencyDeserialize.class));
        CLASS_INSTANCE_MAP.put(Date.class, Instances.singleton(DateDeserialize.class));

        CLASS_INSTANCE_MAP.put(Object.class, Instances.singleton(ObjectDeserialize.class));
    }

    /**
     * 获取反序列化实现
     * @param json json
     * @param clazz 类
     * @return 结果
     * @since 0.0.1
     */
    public static IDeserialize getDeserialize(final String json, final Class clazz) {
        if(JsonConst.NULL.equals(json)) {
            return Instances.singleton(NullDeserialize.class);
        }
        // 直接映射对应处理类
        IDeserialize deserialize = CLASS_INSTANCE_MAP.get(clazz);
        if(ObjectUtil.isNotNull(deserialize)) {
            return deserialize;
        }

        // 聚合类
        // 枚举
        if(clazz.isEnum()) {
            return Instances.singleton(EnumDeserialize.class);
        }
        // 数组
        if(ClassTypeUtil.isArray(clazz)) {
            return getArrayDeserialize(clazz);
        }
        // 集合
        if(ClassTypeUtil.isCollection(clazz)) {
            return Instances.singleton(CollectionDeserialize.class);
        }
        // map
        if(ClassTypeUtil.isMap(clazz)) {
            return Instances.singleton(MapDeserialize.class);
        }

       return Instances.singleton(BeanDeserialize.class);
    }

    /**
     * 获取数组反序列化类
     * @param clazz 类型
     * @since 0.0.4
     */
    private static IDeserialize getArrayDeserialize(final Class clazz) {
        if(int[].class.equals(clazz)) {
            return Instances.singleton(ArrayIntDeserialize.class);
        } else if(long[].class.equals(clazz)) {
            return Instances.singleton(ArrayLongDeserialize.class);
        } else if(float[].class.equals(clazz)) {
            return Instances.singleton(ArrayFloatDeserialize.class);
        } else if(double[].class.equals(clazz)) {
            return Instances.singleton(ArrayDoubleDeserialize.class);
        } else if(byte[].class.equals(clazz)) {
            return Instances.singleton(ArrayByteDeserialize.class);
        } else if(char[].class.equals(clazz)) {
            return Instances.singleton(ArrayCharDeserialize.class);
        } else if(short[].class.equals(clazz)) {
            return Instances.singleton(ArrayShortDeserialize.class);
        } else if(boolean[].class.equals(clazz)) {
            return Instances.singleton(ArrayBooleanDeserialize.class);
        }

        return Instances.singleton(ArrayDeserialize.class);
    }

    /**
     * 获取反序列化实现
     * （1）默认不为空
     * @param clazz 类
     * @return 结果
     * @since 0.0.4
     */
    public static IDeserialize getDeserialize(final Class clazz) {
        return getDeserialize("notnull", clazz);
    }

    /**
     * 是否为 java 对象类型
     * @param clazz class
     * @return 是否
     * @since 0.0.8
     */
    public static boolean isBeanDeserializeType(final Class clazz) {
        if(CLASS_INSTANCE_MAP.keySet().contains(clazz)) {
            return false;
        }
        // 枚举
        if(clazz.isEnum()) {
            return false;
        }
        // 数组
        if(ClassTypeUtil.isArray(clazz)) {
            return false;
        }
        // 集合
        if(ClassTypeUtil.isCollection(clazz)) {
            return false;
        }
        // map
        if(ClassTypeUtil.isMap(clazz)) {
            return false;
        }
        if(ClassTypeUtil.isJdk(clazz)) {
            return false;
        }

        // 其他返回 true
        return true;
    }

}
