package com.github.houbb.json.test.support.scanner;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.tuple.impl.Pair;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.json.support.scanner.impl.JsonFieldMetaScanner;
import org.junit.Test;

import java.util.List;

/**
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

