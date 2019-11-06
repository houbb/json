package com.github.houbb.json.support.scanner.impl;

import com.github.houbb.heaven.annotation.CommonEager;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.scanner.IJsonScanner;

import java.util.Collections;
import java.util.List;

/**
 * 可遍历扫描接口类
 *
 * 主要针对一些特殊符号的处理【,】
 *
 * @author binbin.hou
 * @see java.lang.reflect.Array 数组
 * @see Iterable 可遍历的结合对象
 * @since 0.1.2
 */
@ThreadSafe
public class JsonArrayObjectScanner implements IJsonScanner<String> {

    /**
     * 只需要考虑 " 和转义字符 \
     *
     * 对象信息，直接是以 {@code {}} 作为单个信息的处理。
     * 1. 下面获取的是非 "" 号内的逗号。
     * 2. 如果获取之后，应该判断这个【,】是否为对象之间的分割符号
     * 3. 如果不是，则直接跳过。
     *
     * 如果是对象间的分割符号，那么前一个字符一定是【}】，直接向前便利即可。
     * @param json        json 内容
     * @return 分组后的列表信息
     */
    @Override
    public List<String> scan(String json) {
        if (StringUtil.isEmpty(json)
                || JsonIterableConst.EMPTY.equals(json)) {
            return Guavas.newArrayList();
        }

        // 去除开头结尾
        final String contentJson = json.substring(1, json.length()-1);
        List<Integer> commaIndexList = StringUtil.getIndexList(contentJson, CharConst.COMMA, true);
        // 获取对象之间的分割符号
        List<Integer> objectSplitIndexes = getObjectSplitIndexes(contentJson, commaIndexList);
        return StringUtil.splitByIndexes(contentJson, objectSplitIndexes);
    }

    /**
     * 获取对象间的分割符号
     * 1. 这里需要进行回溯，实际上性能有所降低，建议直接加一个方法，可以指定前面的符号是什么。
     * @param contentJson 完整的 json 信息
     * @param commaIndexList 对象下标志信息
     * @return 对象间的分割索引
     * @since 0.1.2
     */
    @CommonEager
    private List<Integer> getObjectSplitIndexes(final String contentJson, final List<Integer> commaIndexList) {
        if(CollectionUtil.isEmpty(commaIndexList)) {
            return Guavas.newArrayList();
        }

        List<Integer> resultList = Guavas.newArrayList();
        for(Integer index : commaIndexList) {
            char preChar = getPreNotBlankChar(contentJson, index);
            if(JsonBeanConst.C_END == preChar) {
                resultList.add(index);
            }
        }
        return resultList;
    }

    /**
     * 获取前一个非空的 char
     * （1）如果已经遍历完，则直接返回一个空
     * @param original 原始信息
     * @param index 下表
     * @return char
     */
    private char getPreNotBlankChar(final String original, final int index) {
        for(int i = index; i > 0; i--) {
            char currentChar = original.charAt(i);
            if(currentChar != ' '
                && currentChar != '\n'
                && currentChar != '\r') {
                return currentChar;
            }
        }
        return ' ';
    }

}

