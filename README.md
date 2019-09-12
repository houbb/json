# json

[json](https://github.com/houbb/json) 是 java 实现的序列化 json 框架。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/json/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/json)
[![Build Status](https://www.travis-ci.org/houbb/json.svg?branch=master)](https://www.travis-ci.org/houbb/json)
[![Coverage Status](https://coveralls.io/repos/github/houbb/json/badge.svg?branch=master)](https://coveralls.io/github/houbb/json?branch=master)

## 特性

- 8 大基本类型支持

- 基本类型/对象数组/集合/枚举/对象 支持

- 极简的 API

## 后续特性

- `@Field` 注解的支持

- 反序列化对象的完整性支持，目前只支持基础的反序列化。

## 为何创作

- 解决 fastJson 中的不足

FastJSON 在序列化本身存在一定限制。当对象中有集合，集合中还是对象时，结果不尽如人意。

- 轻量且高效

FastJson 等 json 工具特性丰富，但是考虑过多，也兼容过多。

希望通过严格控制序列化和反序列，从而使得代码变得高效轻量，更加适合自己的使用场景，

- 后续拓展

后续序列化相关，将使用这个工具实现，方便自行拓展学习。

## 变更日志

[变更日志](doc/CHANGE_LOG.md)

# 快速开始

## 环境依赖

JDK 1.7+

Maven 3.X

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
import JsonBs;
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

# 测试案例

以下演示所有测试代码见

> [test 模块](https://github.com/houbb/json/tree/master/src/test/java/com/github/houbb/json/test)

# 字符串和 char 测试案例

## 字符串

```java
 @Test
public void commonTest() {
    final String string = "123";
    Assert.assertEquals("\"123\"", JsonBs.serialize(string));

    final String json = "\"123\"";
    Assert.assertEquals(string, JsonBs.deserialize(json, String.class));
}

@Test
public void escapeTest() {
    final String string = "\"123";
    Assert.assertEquals("\"\"123\"", JsonBs.serialize(string));

    final String json = "\"123\"";
    Assert.assertEquals("123", JsonBs.deserialize(json, String.class));
}
```

## char 类型

```java
@Test
public void escapeTest() {
    char c = '\'';
    final String json = "\"'\"";

    Assert.assertEquals(json, JsonBs.serialize(c));
    assert c == JsonBs.deserialize(json, char.class);
}
```

# 对于数组的支持

## 字符串

```java
@Test
public void stringEmptyTest() {
    String[] strings = new String[]{};
    String json = "[]";

    Assert.assertEquals(json, JsonBs.serialize(strings));
    Assert.assertEquals(strings, JsonBs.deserialize(json, String[].class));
}

@Test
public void stringTest() {
    String[] strings = new String[]{"a", "b", "c"};
    final String json = "[\"a\",\"b\",\"c\"]";
    Assert.assertEquals(json, JsonBs.serialize(strings));
    Assert.assertEquals(strings, JsonBs.deserialize(json, String[].class));
}
```

## 基本类型测试

```java
public void intTest() {
    int[] ints = new int[]{1,2,3};
    final String intJson = "[1,2,3]";
    Assert.assertEquals(intJson, JsonBs.serialize(ints));

    //[1, 2, 3]
    System.out.println(Arrays.toString(JsonBs.deserialize(intJson, int[].class)));
}
```

## 基本对象类型测试

```java
public void integerTest() {
    Integer[] ints = new Integer[]{1,2,3};
    final String json = "[1,2,3]";
    Assert.assertEquals(json, JsonBs.serialize(ints));

    //[1, 2, 3]
    System.out.println(Arrays.toString(JsonBs.deserialize(json, Integer[].class)));
}
```

# 集合

## 字符串列表测试

```java
public void stringTest() {
    List<String> strings = new ArrayList<>();
    strings.add("10");
    strings.add("20");
    strings.add("30");

    Class clazz = strings.getClass();

    final String json = "[\"10\",\"20\",\"30\"]";
    Assert.assertEquals(json, JsonBs.serialize(strings));
    Assert.assertEquals(strings, JsonBs.deserialize(json, clazz));
}
```

## 字符串 Map 测试

```java
public void stringTest() {
    Map<String, String> map = new HashMap<>();
    map.put("123", "456");

    final String json = "{\"123\":\"456\"}";
    Assert.assertEquals(json, JsonBs.serialize(map));
    Assert.assertEquals(map, JsonBs.deserialize(json, map.getClass()));
}
```

# 对象

## 基础对象演示

- User.java

```java
public class User {

    private String name;

    private int age;

    private double score;

    private char sex;

    private Date birthday;

    //Getter & Setter
    //ToString()
}
```

- 测试代码

```java
public void userTest() {
    User user = new User();
    user.age(10).name("wiki").birthday(new Date(1568196960491L)).score(123.d).sex('g');

    final String json = "{\"name\":\"wiki\",\"age\":10,\"score\":123.0,\"sex\":\"g\",\"birthday\":1568196960491}";
    Assert.assertEquals(json, JsonBs.serialize(user));

    User user2 = JsonBs.deserialize(json, User.class);
    Assert.assertEquals(user.toString(), user2.toString());
}
```

# 拓展阅读

[00-什么是 json](doc/user/00-what-is-json.md)

[01-模块介绍](doc/user/01-json-modules.md)

