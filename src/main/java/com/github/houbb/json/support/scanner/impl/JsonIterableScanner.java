package com.github.houbb.json.support.scanner.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
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
     * @return 分组后的列表信息
     */
    @Override
    public List<String> scan(String json) {
        if (StringUtil.isEmpty(json)
                || JsonIterableConst.EMPTY.equals(json)) {
            return Collections.emptyList();
        }

        // 去除开头结尾
        final String contentJson = json.substring(1, json.length()-1);
        List<Integer> commaIndexList = StringUtil.getIndexList(contentJson, CharConst.COMMA, true);
        return StringUtil.splitByIndexes(contentJson, commaIndexList);
    }

}

