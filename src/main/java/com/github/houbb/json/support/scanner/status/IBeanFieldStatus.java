package com.github.houbb.json.support.scanner.status;

/**
 * Bean 分割符号 状态管理
 *
 * <p> project: json-IDoubleQuotesStatus </p>
 * <p> create on 2019/11/9 20:35 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
public interface IBeanFieldStatus {

    /**
     * bean 是否开始，如果开始，则进行相关的信息添加。
     * @return 是否处于
     * @since 0.1.4
     */
    boolean isKeyStart();

    /**
     * bean 是否结束
     * @return 是否处于
     * @since 0.1.4
     */
    boolean isKeyEnd();

    /**
     * value 读取是否开始
     * @return 是否开始
     * @since 0.1.4
     */
    boolean isValueStart();

    /**
     * value 读取是否结束
     * @return 是否开始
     * @since 0.1.4
     */
    boolean isValueEnd();

    /**
     * 设置当前的符号
     * @param c 符号
     * @since 0.1.4
     */
    void currentChar(final char c);

}
