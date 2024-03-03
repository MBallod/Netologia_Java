package ru.netology.mballod;

import lombok.Data;

@Data
public class CashbackOperation extends Operation{
    private int cashbackAmount;
    @Override
    public void print(){
        System.out.println("Кэшбэк. Дата: " + this.getDateAsString() + ", сумма: " + this.getAmount() + ", тип: " + this.getDebitAsString() + ", кредитный договор: "+ this.cashbackAmount);
    }
}
