package com.github.houbb.json.test.model;

/**
 * @author binbin.hou
 * @since 0.1.6
 */
public class NotFieldBook {

    public NotFieldBook() {
    }

    private String bookName;

    /**
     * 验证 is
     * @since 0.1.7
     */
    private boolean china;

    public String getName() {
        return bookName;
    }

    public void setName(String name) {
        this.bookName = name;
    }

    public boolean isChina() {
        return china;
    }

    public void setChina(boolean china) {
        this.china = china;
    }
}
