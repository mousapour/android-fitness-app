package com.example.android_fitness_app.Model;

public class User {
    private static User user;
    private String name;
    private double weight;
    private double height;
    private Sex sex;

    private User() {
        name = null;
        height = -1;
        weight = -1;
        sex = Sex.MALE;
    }

    public static User getInstance() {
        if (user == null) user = new User();
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
        System.out.println(this);
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", sex=" + sex +
                '}';
    }
}
