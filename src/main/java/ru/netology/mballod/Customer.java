package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Customer implements ConsolePrintable{
    @Setter
    private String name;
    private int age;
    @Setter
    private double wealth;
    public Customer() {
        this.name = null;
        this.age = 18;
        this.wealth = 0;
    }
    public Customer(String name, int age, double wealth) {
        this.name = name;
        this.age = age;
        this.wealth = wealth;
    }

    public void setAge(int age) {
        this.age = (age > 0 && age < 200) ? age : this.age;
    }
    @Override
    public void print(){
        System.out.println("Имя: " + this.name + ", возраст: " + this.age + " сумма на счете: " + this.wealth);
    }
}
