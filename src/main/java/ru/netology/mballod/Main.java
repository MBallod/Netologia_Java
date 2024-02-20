package ru.netology.mballod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Описание переменных: date - дата транзакции, amount - сумма в рублях, debit - направление платежа: входящий, если true, исходящий, если false");
        Date date;
        double amount;
        boolean debit;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите дату транзакции в формате: y-M-d, например 2024-2-21");
            String DateForInput = scanner.next();
            String pattern = "y-M-d";
            DateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(DateForInput);
            String DateForOutput = df.format(date);

            System.out.println("Введите сумму транзакции, разделяя точкой целую и дробную часть");
            amount = scanner.nextDouble();

            System.out.println("Введите true, если транзакция входящая и false - если исходящая");
            debit = scanner.nextBoolean();

            System.out.println("Дата транзакции: "+DateForOutput + ", сумма в рублях: " + amount + ", входящая (true) / исходящая (false): " + debit);
        } catch (InputMismatchException | ParseException e) {
            System.out.println("Неверный формат ввода");
        }

        }
    }
