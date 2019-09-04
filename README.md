# json

序列化工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/json/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/json)
[![Build Status](https://www.travis-ci.org/houbb/json.svg?branch=master)](https://www.travis-ci.org/houbb/json)
[![Coverage Status](https://coveralls.io/repos/github/houbb/json/badge.svg?branch=master)](https://coveralls.io/github/houbb/json?branch=master)

# 快速开始

## maven 依赖

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>json-core</artifactId>
    <version>最新版本</version>
</dependency>
```

## 基本例子

```java
import com.github.houbb.json.core.bs.JsonBs;
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
```

# 拓展阅读

[00-什么是 json](doc/user/00-what-is-json.md)

[01-模块介绍](doc/user/01-json-modules.md)

