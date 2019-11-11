package com.github.houbb.json.support.context;

import com.github.houbb.heaven.support.attr.IAttributeContext;
import com.github.houbb.json.support.config.ISerializeConfig;

/**
 * 序列化上下文
 *
 * 核心目的：
 * （1）便于后续拓展，而保证接口不变化。
 * （2）保证 scan-once
 * @author binbin.hou
 * @since 0.1.6
 */
public interface ISerializeContext extends IAttributeContext {

    /**
     * 配置信息
     * @return 配置信息
     * @since 0.1.6
     */
    ISerializeConfig config();

    /**
     * 原始对象
     * @return 目标对象
     * @since 0.1.6
     */
    Object object();

}
