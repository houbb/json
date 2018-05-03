package com.github.houbb.api;

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
public interface JsonApi {

    /**
     * 转化为Json
     * @param object
     * @return
     */
    String toJson(Object object);

    /**
     * 转换为对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T parseObject(final String json, final Class<T> tClass);

    /**
     * 转换为列表
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> parseArray(final String json, final Class<T> tClass);

}
