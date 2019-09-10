package com.github.houbb.json.test.model;


import java.util.Date;

/**
 * <p> 用户 </p>
 *
 * <pre> Created: 2019/9/11 7:52 AM  </pre>
 * <pre> Project: json  </pre>
 *
 * @author houbinbin
 */
public class User {

    private String name;

    private int age;

    private double score;

    private char sex;

    private Date birthday;

    public String name() {
        return name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public int age() {
        return age;
    }

    public User age(int age) {
        this.age = age;
        return this;
    }

    public double score() {
        return score;
    }

    public User score(double score) {
        this.score = score;
        return this;
    }

    public char sex() {
        return sex;
    }

    public User sex(char sex) {
        this.sex = sex;
        return this;
    }

    public Date birthday() {
        return birthday;
    }

    public User birthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", sex=" + sex +
                ", birthday=" + birthday +
                '}';
    }
}
