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
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;

/**
 * 可遍历扫描接口类
 *
 * 主要针对一些特殊符号的处理【,:】
 *
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class JsonMapScanner implements IJsonScanner<Pair<String, String>> {

    @Override
    public List<Pair<String, String>> scan(String json, IDeserialize... deserialize) {
        return null;
    }

}

