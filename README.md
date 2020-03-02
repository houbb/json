# json

[json](https://github.com/houbb/json) 是 java 实现的序列化 json 框架。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/json/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/json)

## 特性

- 8 大基本类型支持

- 基本类型/对象数组/集合/枚举/对象 支持

- 极简的 API

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
    <artifactId>json</artifactId>
    <version>${最新版本}</version>
</dependency>
```

## 基本例子

### 序列化

```java
int[] ints = new int[]{1,2,3};
String json = JsonBs.serialize(ints);
Assert.assertEquals("[1,2,3]", json);
```

### 反序列化

```java
final String json = "[1,2,3]";
int[] ints = new int[]{1,2,3};
Assert.assertArrayEquals(ints, JsonBs.deserialize(json, int[].class));
```

### 反序列化列表

```java
final String json = "[1,2,3]";
List<Integer> integerList = JsonBs.deserializeArray(json, Integer.class);
Assert.assertEquals(3, integerList.size());
```

# 更多特性

## 序列化是否保留 null 值

- 说明

默认 null 值不保留。

- 测试代码

```java
Book book = new Book();

String json = JsonBs.serialize(book);
Assert.assertEquals("{}", json);

final ISerializeConfig config = SerializeConfig.newInstance().nullRemains(true);
String nullJson = JsonBs.serialize(book, config);
Assert.assertEquals("{\"name\":null}", nullJson);
```

- Book.java

```java
public class Book {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## 是否基于 field

- 说明

默认基于 field 进行序列化和反序列化处理。

当然如果不指定，则以方法为准。

- 测试代码

```java
NotFieldBook book = new NotFieldBook();
book.setName("hello");

final String json = "{\"name\":\"hello\"}";
Assert.assertEquals(json, JsonBs.serialize(book, SerializeConfig.newInstance().fieldBased(false)));

final IDeserializeConfig deserializeConfig = DeserializeConfig.newInstance().fieldBased(false);
NotFieldBook book2 = JsonBs.deserialize(json, NotFieldBook.class, deserializeConfig);
Assert.assertEquals("hello", book2.getName());
```

- NotFieldBook.java

```java
public class NotFieldBook {

    private String bookName;

    public String getName() {
        return bookName;
    }

    public void setName(String name) {
        this.bookName = name;
    }
}
```
