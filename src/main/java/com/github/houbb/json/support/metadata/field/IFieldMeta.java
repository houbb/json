package com.github.houbb.json.support.metadata.field;

/**
 * <p> project: json-IFieldMeta </p>
 * <p> create on 2019/11/9 23:08 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
public interface IFieldMeta {

    /**
     * 设置名称
     * @param name 名称
     * @since 0.1.4
     */
    void setName(final String name);

    /**
     * 字段名称
     * @return 名称
     * @since 0.1.4
     */
    String getName();

    /**
     * 设置字段类型
     * @param type 类型
     * @since 0.1.4
     */
    void setType(final Class type);

    /**
     * 字段类型
     * @return 字段类型
     * @since 0.1.4
     */
    Class getType();

    /**
     * 字段值
     * @return 字段值
     */
    Object getValue();

    /**
     * 字段值
     * @param value 设置值
     */
    void setValue(final Object value);

}
