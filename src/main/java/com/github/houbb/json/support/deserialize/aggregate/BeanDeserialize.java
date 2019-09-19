package com.github.houbb.json.support.deserialize.aggregate;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.reflect.ClassTypeUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.util.ArrayPrimitiveUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.constant.JsonMapConst;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.deserialize.DeserializeFactory;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 对象反序列化
 * @author binbin.hou
 * @since 0.0.5
 * @param <T> 泛型
 */
public class BeanDeserialize<T> implements IDeserialize<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String json, Class aClass) {
        // 类如果是 java 对象才处理
        if(ClassTypeUtil.isJdk(aClass)) {
            return (T) Instances.singleton(ObjectDeserialize.class)
                    .deserialize(json, aClass);
        }
        List<Field> fieldList = ClassUtil.getAllFieldList(aClass);
        if(CollectionUtil.isEmpty(fieldList)) {
            return (T) Instances.singleton(ObjectDeserialize.class)
                    .deserialize(json, aClass);
        }

        T instance = (T) ClassUtil.newInstance(aClass);
        if(JsonBeanConst.NULL.equals(json)) {
            return instance;
        }
        // 处理
        List<Pair<Field, Object>> fieldPairs = getFieldValueList(json, fieldList);
        if(CollectionUtil.isEmpty(fieldPairs)) {
            return instance;
        }
        // 循环设置值
        try {
            for(Pair<Field, Object> pair : fieldPairs) {
                Field field = pair.getValueOne();
                Object value = pair.getValueTwo();
                field.set(instance, value);
            }
        } catch (IllegalAccessException e) {
            throw new JsonRuntimeException(JsonRespCode.DES_ILLEGAL_ACCESS);
        }

        return instance;
    }

    /**
     * 获取字段的值
     *
     * {"name":"wiki","age":10,"score":123.0,"sex":"g","birthday":1568190883854}
     *
     * 存在的问题：
     * （1）如果属性中存在对象/集合/数组/Map，将会让反序列化变得非常复杂。
     * （2）即使根据排序去反序列化，是否可以简单化。
     *
     * 我们假定遇到的第一个 fieldName 和 json 中的匹配，结合该字段的实际类型。
     * 去直接 subString() 截取到我们的字符串，然后反序列化得到结果。
     *
     * （3）如何快速定位一个 field 的开始位置。
     * 直接【indexOf("fieldName"):】会有几个结果？
     * 为了提升效率，直接遍历一遍字符串。获取所有的字段开始位置。
     *
     * 3.1 如果 indexOf 字段发现多个返回 index 怎么办
     * （1）首先保证，按照顺序来解析。已经过去的位置，不再参与搜索，降低重复的概率。
     * （2）如果发生重复的返回值下标，则判断下标的位置，一般应该是紧挨着下一个位置，才会做为 fieldName。特别靠后的位置，
     * 是不能参与的。
     * （3）如果处于简单性考虑，只可以直接禁止添加多余的空格和换行的。这样可以更加简单的判断。
     *
     * 当着这里是快速模式。为了性能考虑，暂时只支持快速模式。
     *
     * 3.2 如何获取属性的值。
     *
     * 直接获取【:】之后和【,】之前的内容。
     * 且【,】不位于任何 "" 特殊符号之中。
     *
     * 4. 如果需要递归定位，则应该使用递归的方式。
     *
     * TODO: 这里不支持集合、数组、对象、Map
     *
     * 因为这其中包含 , 号，会直接打乱定位。
     *
     * 如果存放的元素都是普通元素，那么：
     *
     * 4.1 集合/数组  需要以 ] 结尾
     * 4.2 Map/对象 需要以 } 结尾
     *
     * 如果存放的元素，本身包含特殊元素，则需要进一步考虑。
     * @param json json 信息
     * @param fieldList 字段列表
     * @return 结果
     * @since 0.0.7
     */
    private List<Pair<Field, Object>> getFieldValueList(final String json, final List<Field> fieldList) {
        List<Pair<Field, Object>> resultList = Guavas.newArrayList(fieldList.size());
        final String contentJson = json.substring(1, json.length()-1);
        final char[] contentChars = contentJson.toCharArray();

        int lastKeyIndex = 0;
        for(Field field : fieldList) {
            String fieldName = field.getName();
            Class fieldType = field.getType();

            final String fieldNameKey = PunctuationConst.DOUBLE_QUOTES+fieldName+PunctuationConst.DOUBLE_QUOTES
                    +PunctuationConst.COLON;
            int fieldNameIndex = contentJson.indexOf(fieldNameKey, lastKeyIndex);
            // 暂时使用严格模式，后续可以使用空格判断。
            // 这里直接进行判断是不严谨的，可能其他位置也会有这种内容。
            if(fieldNameIndex < 0) {
                continue;
            }
            // 从 fieldNameIndex 开始处理后续的 value 信息
            // 遍历后续的信息，直接到 , 开始终止
            int valueStartIndex = lastKeyIndex+fieldNameKey.length();
            String valueJson = this.getValueJson(contentChars, valueStartIndex, fieldType);

            // 直接反射处理对象信息
            Object value = JsonBs.deserialize(valueJson, fieldType);
            Pair<Field, Object> pair = Pair.of(field, value);
            resultList.add(pair);

            // 更新下标信息
            // key 的开始+value 的长度，加一个逗号的长度。
            lastKeyIndex = valueStartIndex+valueJson.length()+1;
        }

        return resultList;
    }

    /**
     * 获取值对应的 json 信息
     * @param chars 字符列表
     * @param valueStartIndex 开始下标
     * @param fieldType 字段类型
     * @return 结果
     * @since 0.0.8
     */
    private String getValueJson(final char[] chars, final int valueStartIndex, final Class fieldType) {
        char endChar = CharConst.COMMA;
        if(ClassTypeUtil.isArray(fieldType)
            || ClassTypeUtil.isCollection(fieldType)) {
            endChar = JsonIterableConst.C_END;
        } else if(ClassTypeUtil.isMap(fieldType)
            || DeserializeFactory.isBeanDeserializeType(fieldType)) {
            endChar = JsonMapConst.C_END;
        }

        // 对于数组的反序列化，最后的 ] 缺失了。同理 map/bean 也会存在相同的问题。
        String tempString = ArrayPrimitiveUtil.getStringBeforeSymbol(chars, valueStartIndex,
                endChar);
        if(endChar != CharConst.COMMA) {
            tempString += endChar;
        }
        return tempString;
    }

}
