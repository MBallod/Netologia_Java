package ru.netology.mballod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Описание переменных: date - дата транзакции, amount - сумма в рублях, debit - направление платежа: входящий, если true, исходящий, если false");
        LocalDate date;
        double amount;
        boolean debit;
        Scanner scanner = new Scanner(System.in);
        String pattern = "y-M-d";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        final int N_OF_TRANSACTIONS = 5;
        int counter = 1;
        System.out.println("Общее число транзакций: "+ N_OF_TRANSACTIONS);
        StringBuilder builder = new StringBuilder();

        while (true) {
            System.out.println("Введите информацию о транзакции номер "+ counter);
            try {
                System.out.println("Введите дату транзакции в формате: y-M-d, например 2024-2-21");
                String DateForInput = scanner.next();
                date = LocalDate.parse(DateForInput, formatter);
                String DateForOutput = date.format(formatter);

                System.out.println("Введите сумму транзакции в рублях, разделяя точкой целую и дробную часть");
                amount = scanner.nextDouble();

                System.out.println("Транзакция входящая (y) или исходящая (n)?");
                String InOrOutTransaction = scanner.next();
                if (InOrOutTransaction.equals("y")){
                    debit = true;
                } else if (InOrOutTransaction.equals("n")) {
                    debit = false;
                } else {
                    //System.out.println("Неверный формат ввода. Придется ввести информацию снова");
                    throw new InputMismatchException("Неверный формат ввода");
                }

                builder.append("Транзация ").append(counter).append(" -  Дата: ").append(DateForOutput).append(", сумма в рублях: ").append(amount).append(", входящая (true) / исходящая (false): ").append(debit).append("\n");
            } catch (InputMismatchException | DateTimeParseException e) {
                System.out.println("Неверный формат ввода");
            }
            if (counter == N_OF_TRANSACTIONS) {
                break;
            }
            counter ++;
        }
        String StringOutput = builder.toString();
        System.out.println(StringOutput);
    }
    }
