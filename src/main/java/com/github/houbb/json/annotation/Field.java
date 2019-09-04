package com.github.houbb.json.annotation;

import java.lang.annotation.*;

/**
 * 字段属性
 * @author binbin.hou
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Field {

    /**
     * 格式化
     * （1）金额
     * （2）日期
     * @return 格式化
     * @since 0.0.1
     */
    String format() default "";

    /**
     * 是否参与序列化
     * @return 默认参与
     * @since 0.0.1
     */
    boolean serialize() default true;

    /**
     * 是否参与反序列化
     * @return 默认参与
     * @since 0.0.1
     */
    boolean deserialize() default true;

}
