package com.github.houbb.json.support.scanner.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.reflect.meta.field.IFieldMeta;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.support.scanner.IJsonScanner;
import com.github.houbb.json.support.scanner.status.IBeanFieldStatus;
import com.github.houbb.json.support.scanner.status.IBeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.IDoubleQuotesStatus;
import com.github.houbb.json.support.scanner.status.impl.BeanFieldStatus;
import com.github.houbb.json.support.scanner.status.impl.BeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.impl.DoubleQuotesStatus;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * FieldMeta 扫描类
 * @author binbin.hou
 * @since 0.1.4
 */
@ThreadSafe
public class JsonFieldMetaScanner implements IJsonScanner<Pair<String, String>> {

    /**
     * 返回列表信息
     * （1）key 是 {@link IFieldMeta#getName()}
     * （2）value 是 key 对应的 value json 内容。
     *
     * 直接扫描，按照 key:value 分隔。
     *
     *
     * 对象 JSON 示例如下：
     * <pre>
     * {"name":"weightApple","className":"com.github.houbb.ioc.test.service.ColorWeightApple","lazyInit":false,
     * "constructorArgList":[{"ref":"apple"},{"type":"java.lang.Integer","value":"10"}]}
     * </pre>
     *
     * 这里有两大层的关系：
     * （1）不同字段之间
     * （2）字段的 key:value
     *
     * 如果采用一遍读取，直接获取结果的方式，那么就需要一些约定：
     * 1. 什么时候开启读取模式？比如第一个字符为左花括号，则认为读取开始。
     * 2. 什么时候结束读取模式？花括号堆栈为结束时，终止读取。
     *
     * ps: 所有的符号必须不属于转义符号。
     *
     * 3. 哪里是 Key?
     * 3.0 默认状态，为 key-init
     * 3.1 开始读取准备，且进入【"】开始部分-状态为 key-pre
     * 3.2 开始读取，前一个状态为 key-pre，且后一个状态为非特殊字符。key-init
     * 3.2 结束读取，遇到 【"】符号终结-状态为 key-end
     * 4. 哪里是 value?
     * 4.1 默认为 value-init
     * 4.2 开始读取：当遇到 key-end 且 char 为 【:】时-状态为 value-start
     * 4.3 结束读取：当遇到 []{} 全部为空，subEntryStack 为空，且符号为【,】时-状态为 value-end
     * ps: 可能字符串/chars 等类型需要做下处理，也可以不做。
     *
     * @param json json 内容
     * @return 结果列表
     * @since 0.1.4
     */
    @Override
    public List<Pair<String, String>> scan(String json) {
        LinkedList<Pair<String, String>> resultList = new LinkedList<>();

        //fail-fast
        if(StringUtil.isEmpty(json)
            || JsonBeanConst.NULL.equals(json)) {
            return resultList;
        }

        // 核心流程
        final IDoubleQuotesStatus quotesStatus = new DoubleQuotesStatus();
        final IBeanSplitterStatus splitterStatus = new BeanSplitterStatus();
        final IBeanFieldStatus fieldStatus = new BeanFieldStatus();

        char[] chars = json.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : chars) {
            System.out.print(c);

            // 设置当前字段
            quotesStatus.currentChar(c);

            // 不处于字符串中，进行 bean 符号的相关处理。
            if(!quotesStatus.isInQuote()) {
                // 对象分隔符状态流转
                splitterStatus.currentChar(c);

                // 对象级别信息改变
                if(splitterStatus.isBeanEnd()) {
                    setValueJson(resultList, stringBuilder);
                    break;
                }
            }

            //bean 开始，才关心 field 相关的状态变化
            if(splitterStatus.isBeanStart()) {
                //field 级别状态流转。
                fieldStatus.currentChar(c);
            }

            //field 相关字段处理
            if(fieldStatus.isKeyStart()
                || fieldStatus.isValueStart()) {
                // 持续添加 key
                stringBuilder.append(c);
            } else if(fieldStatus.isKeyEnd()) {
                // 放入 key，清空 build
                final String key = stringBuilder.toString();
                Pair<String, String> pair = Pair.of(key, null);
                resultList.add(pair);

                stringBuilder = new StringBuilder();
            } else if(fieldStatus.isValueEnd()) {
                // 放入 value，清空 build
                setValueJson(resultList, stringBuilder);
                stringBuilder = new StringBuilder();
            }
        }

        return resultList;
    }

    /**
     * 设置值信息
     * （1）常规的处理
     * （2）如果是最后一个value，则直接处理即可。不需要再次依赖逗号分隔。
     *
     * ps: 这里有个问题，直接在方法体内情况 builder 实际是无效的。
     * @param resultList 结果列表
     * @param stringBuilder 构造器
     * @since 0.1.4
     */
    private void setValueJson( LinkedList<Pair<String, String>> resultList,
                               StringBuilder stringBuilder) {
        Pair<String, String> pair = resultList.getLast();
        final String value = stringBuilder.toString();
        Pair<String, String> newPair = Pair.of(pair.getValueOne(), value);
        resultList.set(resultList.size()-1, newPair);
    }

}

