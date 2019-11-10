package com.github.houbb.json.test.support.scanner;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.support.metadata.field.IFieldMeta;
import com.github.houbb.json.support.scanner.IJsonScanner;
import com.github.houbb.json.support.scanner.impl.JsonFieldMetaScanner;
import com.github.houbb.json.support.scanner.status.IBeanFieldStatus;
import com.github.houbb.json.support.scanner.status.IBeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.IDoubleQuotesStatus;
import com.github.houbb.json.support.scanner.status.impl.BeanFieldStatus;
import com.github.houbb.json.support.scanner.status.impl.BeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.impl.DoubleQuotesStatus;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 可遍历 {@link com.github.houbb.json.support.metadata.field.impl.FieldMeta} 扫描处理类
 *
 * @author binbin.hou
 * @since 0.1.4
 */
public class JsonFieldMetaScannerTest {

    @Test
    public void jsonTest() {
        final String json = "{\"name\":\"weightApple\",\"className\":\"com.github.houbb.ioc.test.service.ColorWeightApple\",\"lazyInit\":false,\"constructorArgList\":[{\"ref\":\"apple\"},{\"type\":\"java.lang.Integer\",\"value\":\"10\"}]}";

        List<Pair<String,String>> pairs = new JsonFieldMetaScanner().scan(json);
        CollectionUtil.toList(pairs, new IHandler<Pair<String, String>, Object>() {
            @Override
            public Object handle(Pair<String, String> objects) {
                System.out.println(objects);
                return null;
            }
        });
    }
}

