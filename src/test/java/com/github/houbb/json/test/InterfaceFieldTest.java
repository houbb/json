package com.github.houbb.json.test;

import com.github.houbb.heaven.util.lang.reflect.ReflectMethodUtil;
import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.model.BeanDefinition;
import com.github.houbb.json.test.model.ConstructorArgDefinition;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 不过还像可以直接 {@link Class#getMethods()} 获取方法列表
 * @author binbin.hou
 * @since 0.1.4
 */
public class InterfaceFieldTest {

    @Test
    public void fieldTest() {
        Method[] methods = BeanDefinition.class.getDeclaredMethods();

        for(Method method : methods) {
            if(method.getName().startsWith("get")) {
                System.out.println(method.getReturnType());
                System.out.println(method.getGenericReturnType());
                System.out.println(ReflectMethodUtil.getGenericReturnParamType(method, 0));
            }
            System.out.println("\n\n");
        }
    }

    /**
     * 反序列化数组处理
     * @since 0.1.4
     */
    @Test
    public void deserializeArrayTest() {
        final String json = "[{\"ref\":\"apple\"},{\"type\":\"java.lang.Integer\",\"value\":\"10\"}]";

        System.out.println(JsonBs.deserializeArray(json, ConstructorArgDefinition.class));
    }

}
