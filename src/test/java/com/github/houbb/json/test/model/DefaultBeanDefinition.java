package com.github.houbb.json.test.model;

/**
 * 默认对象定义属性
 * @author binbin.hou
 * @since 0.1.2
 */
public class DefaultBeanDefinition {

    /**
     * 名称
     */
    private String name;

    /**
     * 类名称
     */
    private String className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "DefaultBeanDefinition{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

}
