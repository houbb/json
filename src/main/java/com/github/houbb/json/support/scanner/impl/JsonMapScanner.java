package com.github.houbb.json.support.scanner.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.constant.PunctuationConst;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.api.IDeserialize;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.scanner.IJsonScanner;
import com.github.houbb.json.util.DeserializeUtil;
import com.github.houbb.json.util.JsonIterableUtil;

import java.util.List;


/**
 * 可遍历扫描接口类
 *
 * 主要针对一些特殊符号的处理【,:】
 *
 * （1）key 的处理
 * 直接判断开始是否为 "，如果是，则开启进入模式。
 *
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class JsonMapScanner implements IJsonScanner<Pair<String, String>> {

    @Override
    public List<Pair<String, String>> scan(String json) {
        // 去除开头结尾
        final String contentJson = json.substring(1, json.length()-1);
        List<Integer> commaIndexList = StringUtil.getIndexList(contentJson, CharConst.COMMA, true);
        List<String> iters = StringUtil.splitByIndexes(contentJson, commaIndexList);

        List<Pair<String, String>> resultList = Guavas.newArrayList(iters.size());
        for(String entry : iters) {
            List<Integer> colonIndexList = StringUtil.getIndexList(contentJson, CharConst.COLON, true);
            List<String> entries = StringUtil.splitByIndexes(entry, colonIndexList);
            Pair<String, String> pair = Pair.of(entries.get(0), entries.get(1));
            resultList.add(pair);
        }

        return resultList;
    }

}

