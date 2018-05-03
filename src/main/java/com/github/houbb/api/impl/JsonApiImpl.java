package com.github.houbb.api.impl;

import com.github.houbb.api.JsonApi;

import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/5/3 下午10:15  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonApiImpl implements JsonApi {


    @Override
    public String toJson(Object object) {
        return null;
    }

    @Override
    public <T> T parseObject(String json, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> List<T> parseArray(String json, Class<T> tClass) {
        return null;
    }

}
