/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * json All rights reserved.
 */

package com.github.houbb.json.support.config;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-05-04 09:36  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonConfig {

    /**
     * 更友好的输出形式
     */
    private boolean pretty;

    /**
     * 强制获取字段的值
     */
    private boolean force;

    /**
     * 过滤掉为 null 的值
     */
    private boolean filterNull;

    public JsonConfig(boolean pretty, boolean force, boolean filterNull) {
        this.pretty = pretty;
        this.force = force;
        this.filterNull = filterNull;
    }

    public boolean isPretty() {
        return pretty;
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public boolean isFilterNull() {
        return filterNull;
    }

    public void setFilterNull(boolean filterNull) {
        this.filterNull = filterNull;
    }

    public JsonConfigBuilder builder() {
        return new JsonConfigBuilder();
    }

    public static class JsonConfigBuilder {
        private boolean pretty;
        private boolean force;
        private boolean filterNull;

        public JsonConfigBuilder pretty(final boolean pretty) {
            this.pretty = pretty;
            return this;
        }
        public JsonConfigBuilder force(final boolean force) {
            this.force = force;
            return this;
        }
        public JsonConfigBuilder filterNull(final boolean filterNull) {
            this.filterNull = filterNull;
            return this;
        }

        public JsonConfig build() {
            return new JsonConfig(pretty, force, filterNull);
        }
    }
}
