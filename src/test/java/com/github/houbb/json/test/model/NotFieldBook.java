package com.github.houbb.json.test.model;

/**
 * @author binbin.hou
 * @since 0.1.6
 */
public class NotFieldBook {

    private String bookName;

    public String getName() {
        return bookName;
    }

    public void setName(String name) {
        this.bookName = name;
    }

    @Override
    public String toString() {
        return "NotFieldBook{" +
                "bookName='" + bookName + '\'' +
                '}';
    }

}
