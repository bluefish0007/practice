package com.bluefish.domain;

/**
 * Created by elaine on 2016/4/3.
 */
public class User {

    private int id;
    private String name;
    private int age2;

    public User(){

    }

    public User(String name, int age2){
        this.name = name;
        this.age2 = age2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age2;
    }

    public void setAge(int age) {
        this.age2 = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age2 +
                '}';
    }
}
