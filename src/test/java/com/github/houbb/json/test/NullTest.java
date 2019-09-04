package com.github.houbb.json.test;

import com.github.houbb.json.bs.JsonBs;
import org.junit.Assert;
import org.junit.Test;

/**
 * null 测试
 * @author binbin.hou
 * @since 0.0.1
 */
public class NullTest {

    @Test
    public void nullTest() {
        final String string = null;
        Assert.assertEquals("null", JsonBs.serialize(string));

        final String result = JsonBs.deserialize("null", String.class);
        Assert.assertNull(result);
    }

}
