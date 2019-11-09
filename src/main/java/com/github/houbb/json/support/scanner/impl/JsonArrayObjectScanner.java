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
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.scanner.IJsonScanner;
import com.github.houbb.json.support.scanner.status.IBeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.IDoubleQuotesStatus;
import com.github.houbb.json.support.scanner.status.impl.BeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.impl.DoubleQuotesStatus;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
        return getObjectJsonList(contentJson);
    }

    /**
     * 获取分开的 json 对象信息
     * （1）获取第一个左边花括号，push 堆栈中。
     * （2）当碰到结尾的右边花括号时，pop 出栈。
     *
     * 专门负责，比如 "" 等各种转移字符的处理。
     * @param contentJson json 内容
     * @return 结果列表
     * @since 0.1.4
     */
    private List<String> getObjectJsonList(final String contentJson) {
        List<String> stringList = Guavas.newArrayList();

        final IDoubleQuotesStatus quotesStatus = new DoubleQuotesStatus();
        final IBeanSplitterStatus splitterStatus = new BeanSplitterStatus();

        char[] chars = contentJson.toCharArray();
        StringBuilder beanJsonBuilder = new StringBuilder();
        for(char c : chars) {
            // 设置当前字段
            quotesStatus.currentChar(c);

            // 不处于字符串中，进行 bean 符号的相关处理。
            if(!quotesStatus.isInQuote()) {
                // 分隔符状态流转
                splitterStatus.currentChar(c);
            }

            // 如果对象为开始，则添加进入
            if(splitterStatus.isBeanStart()) {
                beanJsonBuilder.append(c);
            }
            // 结束，则将该字符串放入列表中
            // 重置 Builder
            if(splitterStatus.isBeanEnd()) {
                beanJsonBuilder.append(c);
                String beanJson = beanJsonBuilder.toString();
                stringList.add(beanJson.trim());

                beanJsonBuilder = new StringBuilder();
            }
        }

        return stringList;
    }

}

