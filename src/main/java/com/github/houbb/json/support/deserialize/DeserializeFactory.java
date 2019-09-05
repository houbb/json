package com.github.houbb.json.support.deserialize;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.constant.JsonConst;
import com.github.houbb.json.support.deserialize.math.BigDecimalDeserialize;
import com.github.houbb.json.support.deserialize.math.BigIntegerDeserialize;
import com.github.houbb.json.support.deserialize.util.CurrencyDeserialize;
import com.github.houbb.json.support.deserialize.util.DateDeserialize;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
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
    private static final Map<Class, IDeserialize> CLASS_INSTANCE_MAP = new HashMap<>();

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

        CLASS_INSTANCE_MAP.put(BigDecimal.class, Instances.singleton(BigDecimalDeserialize.class));
        CLASS_INSTANCE_MAP.put(BigInteger.class, Instances.singleton(BigIntegerDeserialize.class));

        CLASS_INSTANCE_MAP.put(Currency.class, Instances.singleton(CurrencyDeserialize.class));
        CLASS_INSTANCE_MAP.put(Date.class, Instances.singleton(DateDeserialize.class));
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

        //@since 0.0.2
        return CLASS_INSTANCE_MAP.get(clazz);
    }

}
