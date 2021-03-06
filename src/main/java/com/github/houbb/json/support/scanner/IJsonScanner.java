package com.github.houbb.json.support.scanner;

import java.util.List;

/**
 * 扫描接口类
 *
 * 主要针对一些特殊符号的处理【,:[]{}】
 * @author binbin.hou
 * @since 0.0.4
 * @param <T> 泛型
 */
public interface IJsonScanner<T> {

    /**
     * 扫描
     * @param json json 内容
     * @return 字符串列表
     * @since 0.0.4
     */
    List<T> scan(final String json);

}
