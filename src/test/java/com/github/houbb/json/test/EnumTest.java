package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import com.github.houbb.json.test.enums.MethodEnum;
import com.github.houbb.json.test.model.PersistAofBean;
import org.junit.Assert;
import org.junit.Test;

/**
 * 枚举测试
 * @author binbin.hou
 * @since 0.1.0
 */
public class EnumTest {

    @Test
    public void enumTest() {
        PersistAofBean bean = new PersistAofBean();
        bean.setMethod(MethodEnum.PUT);
        bean.setParams(new Object[]{"1", "2"});

        String json = "{\"nanoTime\":0,\"method\":\"PUT\",\"params\":[\"1\",\"2\"]}";
        Assert.assertEquals(json, JsonBs.serialize(bean));

        PersistAofBean result = JsonBs.deserialize(json, PersistAofBean.class);
        System.out.println(result);
    }

}
