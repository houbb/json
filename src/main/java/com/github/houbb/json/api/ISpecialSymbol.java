package com.github.houbb.json.api;

/**
 * 用来标识序列化和反序列化，会有字符串的处理。
 * 因为字符串的处理就会涉及到特殊字符，比如【,:{}[]】等等
 *
 * 这个接口可以标识有些序列化不会产生这些字符，因此可以简单暴力的进行分隔。
 *
 * 注意：
 * （1）本接口不包含任何方法，仅仅用来标记是否可能含有特殊符号。
 * @author binbin.hou
 * @since 0.0.4
 */
public interface ISpecialSymbol {
}
