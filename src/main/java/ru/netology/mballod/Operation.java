package ru.netology.mballod;

import java.time.LocalDate;

public class Operation {
    private LocalDate date;
    private double amount;
    private boolean debit;

    public Operation(){}
    public Operation(LocalDate date, double amount, boolean debit){
        this.date = date;
        this.amount = amount;
        this.debit = debit;
    }

    public void setDate(LocalDate date){
        if (date.isBefore(LocalDate.now())) {
            this.date = date;
        }
    }
    public void setAmount(double amount){
        if (amount>0) {
            this.amount = amount;
        }
    }
    public void setDebit(boolean debit){
        this.debit = debit;
    }
    public LocalDate getDate(){
        return date;
    }
    public double getAmount(){
        return amount;
    }
    public boolean getDebit(){
        return debit;
    }
}
