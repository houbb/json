package com.github.houbb.json.support.scanner.status;

/**
 * 双引号状态管理
 *
 * 1. 状态流转不是简单的 boolean 值可以完全表达的。
 *
 * 0 未开始
 * 1 开始，处于双引号中
 * 2 结束，不处于双引号中
 *
 * <p> project: json-IDoubleQuotesStatus </p>
 * <p> create on 2019/11/9 20:35 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
public interface IDoubleQuotesStatus {

    /**
     * 是否处于双引号中
     * @return 是否处于
     * @since 0.1.4
     */
    boolean isInQuote();

    /**
     * 设置当前的符号
     * @param c 符号
     * @since 0.1.4
     */
    void currentChar(final char c);

}
