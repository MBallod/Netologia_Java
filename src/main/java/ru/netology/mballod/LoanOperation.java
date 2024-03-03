package ru.netology.mballod;

import lombok.Data;

@Data
public class LoanOperation extends Operation{
    private int loanId;
    @Override
    public void print(){
        System.out.println("Обслуживание заема. Дата: " + this.getDateAsString() + ", сумма: " + this.getAmount() + ", тип: " + this.getDebitAsString() + ", кредитный договор: "+ this.loanId);
    }
}
