package com.github.houbb.json.test.model;

import java.util.List;

/**
 * 对象定义属性
 * @author binbin.hou
 * @since 0.1.4
 */
public interface BeanDefinition {

    /**
     * 名称
     * @return 名称
     * @since 0.0.1
     */
    String getName();

    /**
     * 设置名称
     * @param name 名称
     * @since 0.0.1
     */
    void setName(final String name);

    /**
     * 类名称
     * @return 类名称
     * @since 0.0.1
     */
    String getClassName();

    /**
     * 设置类名称
     * @param className 类名称
     * @since 0.0.1
     */
    void setClassName(final String className);

    /**
     * 获取生命周期
     * @return 获取生命周期
     * @since 0.0.3
     */
    String getScope();

    /**
     * 设置是否单例
     * @param scope 是否单例
     * @since 0.0.3
     */
    void setScope(final String scope);

    /**
     * 是否为延迟加载
     * @return 是否
     * @since 0.0.3
     */
    boolean isLazyInit();

    /**
     * 设置是否为延迟加载
     * @param isLazyInit 是否为延迟加载
     * @since 0.0.3
     */
    void setLazyInit(final boolean isLazyInit);

    /**
     * 设置初始化方法
     * @param initialize 初始化方法名称
     * @since 0.0.4
     */
    void setInitialize(final String initialize);

    /**
     * 获取初始化方法
     * @return 初始化方法
     * @since 0.0.4
     */
    String getInitialize();

    /**
     * 设置销毁方法
     * @param destroy 销毁方法名称
     * @since 0.0.4
     */
    void setDestroy(final String destroy);

    /**
     * 获取销毁方法
     * @return 销毁
     * @since 0.0.4
     */
    String getDestroy();

    /**
     * 工厂类方法
     * @param factoryMethod 工厂类方法
     * @since 0.0.6
     */
    void setFactoryMethod(final String factoryMethod);

    /**
     * 获取工厂类方法名称
     * @return 工厂类方法名称
     * @since 0.0.6
     */
    String getFactoryMethod();

    /**
     * 构造器参数列表
     * @return 构造器参数列表
     * @since 0.0.6
     */
    List<ConstructorArgDefinition> getConstructorArgList();

    /**
     * 设置构造器参数定义列表
     * @param constructorArgList 构造器参数列表
     * @since 0.0.6
     */
    void setConstructorArgList(final List<ConstructorArgDefinition> constructorArgList);

}
