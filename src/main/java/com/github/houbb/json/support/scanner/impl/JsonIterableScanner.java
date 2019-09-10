package com.github.houbb.json.support.scanner.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.api.ISpecialSymbol;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.scanner.IJsonScanner;
import com.github.houbb.json.util.JsonIterableUtil;

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
 * @since 0.0.4
 */
@ThreadSafe
public class JsonIterableScanner implements IJsonScanner<String> {

    /**
     * 只需要考虑 " 和转义字符 \
     *
     * @param json        json 内容
     * @param deserialize 反序列对象
     * @return 分组后的列表信息
     */
    @Override
    public List<String> scan(String json, IDeserialize... deserialize) {
        if (StringUtil.isEmpty(json)
                || JsonIterableConst.EMPTY.equals(json)) {
            return Collections.emptyList();
        }

        final IDeserialize firstDeserialize = deserialize[0];

        if(firstDeserialize instanceof ISpecialSymbol) {
            char[] chars = json.toCharArray();
            iterableCheck(chars);

            List<String> stringList = Guavas.newArrayList();
            boolean doubleQuotesStart = false;
            char preChar = ' ';
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < chars.length - 1; i++) {
                final char currentChar = chars[i];

                // 上一个字符不是转义，且当前为 "。则进行状态的切换
                if (PunctuationConst.C_BACK_SLASH != preChar
                        && PunctuationConst.C_DOUBLE_QUOTES == currentChar) {
                    // 对标记进行取反操作。
                    doubleQuotesStart = !doubleQuotesStart;

                    // 更新 preChar 状态
                    preChar = getPreChar(preChar, currentChar);

                    // 如果由开始=》结束。
                    // 保存 builder内容，重新初始化 builder
                    if (!doubleQuotesStart) {
                        stringBuilder.append(currentChar);
                        stringList.add(stringBuilder.toString());
                        stringBuilder = new StringBuilder();

                        // 直接跳过后续执行。
                        continue;
                    }
                    // 如果从结束=》开始
                }

                if (doubleQuotesStart) {
                    // 标记为 item 开始，直接添加当前字符。
                    stringBuilder.append(currentChar);
                } else {
                    // item 处于未开始的状态。只允许空格，逗号，
                    if (!JsonIterableUtil.isBlankOrComma(currentChar)) {
                        throw new JsonRuntimeException(JsonRespCode.DES_NEED_COMMA_BLANK);
                    }
                }
            }
            return stringList;
        } else {
            // 去除开头结尾
            String contentJson = json.substring(1, json.length()-1);
            String[] strings = contentJson.split(PunctuationConst.COMMA);
            return CollectionUtil.arrayToList(strings);
        }
    }

    /**
     * 获取上一个字符
     *
     * 保证转义字符的两次抵消。
     *
     * @param preChar     上一个字符
     * @param currentChar 当前字符
     * @return 结果
     * @since 0.0.4
     */
    private char getPreChar(final char preChar, final char currentChar) {
        // 判断前一个字符是什么
        if (PunctuationConst.C_BACK_SLASH == preChar
                && PunctuationConst.C_BACK_SLASH == currentChar) {
            return PunctuationConst.C_BLANK;
        }
        return currentChar;
    }

    /**
     * 信息校验
     * （1）必须以 [ 开始
     * （2）必须以 ] 结束
     *
     * @param chars 信息
     */
    private void iterableCheck(final char[] chars) {
        if (JsonIterableConst.C_START != chars[0]) {
            throw new JsonRuntimeException(JsonRespCode.DES_NEED_ITERABLE_START);
        }
        if (JsonIterableConst.C_END != chars[chars.length - 1]) {
            throw new JsonRuntimeException(JsonRespCode.DES_NEED_ITERABLE_START);
        }
    }

}

