package com.github.houbb.json.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class FastJsonTest {

    @Test
    public void nullTest() {
        System.out.println(JSON.toJSONString(null));

        Assert.assertNull(JSON.toJSONString(null));
        System.out.println(JSON.toJSONString("null"));
    }

}
