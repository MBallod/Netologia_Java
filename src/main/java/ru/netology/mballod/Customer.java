package ru.netology.mballod;

public class Customer {
    private String gender;
    private int age;
    private double wealth;
    public Customer() {
        this.gender = null;
        this.age = 18;
        this.wealth = 0;
    }
    public Customer(String gender, int age, double wealth) {
        this.gender = gender;
        this.age = age;
        this.wealth = wealth;
    }

}
