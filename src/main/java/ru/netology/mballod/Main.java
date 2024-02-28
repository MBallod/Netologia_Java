package ru.netology.mballod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final int N_OF_TRANSACTIONS = 5;
    //массивы с транзакциями и формат записи дат выведены в публичные статические переменные, чтобы их можно было вызывать из статического метода поиска транзакций в датах. Правильно ли это?
    public static LocalDate[] TransactionsDates = new LocalDate[N_OF_TRANSACTIONS];
    public static double[] TransactionsAmounts = new double[N_OF_TRANSACTIONS];
    public static boolean[] TransactionsDebits = new boolean[N_OF_TRANSACTIONS];
    public static String pattern = "y-M-d";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    public static void FindTransactions(LocalDate startDate, LocalDate endDate){
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (int i=0; i<N_OF_TRANSACTIONS; i++){
            if (TransactionsDates[i] == null)
                continue;
            if (TransactionsDates[i].isAfter(startDate.minusDays(1)) && TransactionsDates[i].isBefore(endDate.plusDays(1))){
                String DateForOutput = TransactionsDates[i].format(formatter);
                System.out.println("Транзакция "+ (i+1) + " - Дата: " + DateForOutput + ", сумма в рублях: " + TransactionsAmounts[i] + ", входящая (true) / исходящая (false): " + TransactionsDebits[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Описание переменных: date - дата транзакции, amount - сумма в рублях, debit - направление платежа: входящий, если true, исходящий, если false");
        LocalDate date;
        double amount;
        boolean debit;
        Scanner scanner = new Scanner(System.in);

        int transaction = 1;
        System.out.println("Общее число транзакций: "+ N_OF_TRANSACTIONS);
        StringBuilder builder = new StringBuilder();

        while (true) {
            System.out.println("Введите информацию о транзакции номер "+ transaction);
            try {
                System.out.println("Введите дату транзакции в формате: y-M-d, например 2024-2-21");
                String DateForInput = scanner.next();
                date = LocalDate.parse(DateForInput, formatter);
                String DateForOutput = date.format(formatter);
                TransactionsDates[transaction -1] = date;

                System.out.println("Введите сумму транзакции в рублях, разделяя точкой целую и дробную часть");
                amount = scanner.nextDouble();
                TransactionsAmounts[transaction -1] = amount;

                System.out.println("Транзакция входящая (y) или исходящая (n)?");
                String InOrOutTransaction = scanner.next();
                if (InOrOutTransaction.equals("y")){
                    debit = true;
                } else if (InOrOutTransaction.equals("n")) {
                    debit = false;
                } else {
                    throw new InputMismatchException("Неверный формат ввода");
                }
                TransactionsDebits[transaction -1] = debit;
                //можно было бы обойтись без StringBuilder, но я решил поупражняться
                builder.append("Транзация ").append(transaction).append(" -  Дата: ").append(DateForOutput).append(", сумма в рублях: ").append(amount).append(", входящая (true) / исходящая (false): ").append(debit).append("\n");
            } catch (InputMismatchException | DateTimeParseException e1) {
                System.out.println("Неверный формат ввода");
            }
            if (transaction == N_OF_TRANSACTIONS) {
                break;
            }
            transaction++;
        }
        //вывод информации о всех корректно записанных транзакциях
        String StringOutput = builder.toString();
        System.out.println(StringOutput);

        System.out.println("А сейчас найдем транзакции за заданный период");
        try {
            System.out.println("Введите начальную дату в формате: y-M-d, например 2024-2-21");
            String DateForInput = scanner.next();
            LocalDate startDate = LocalDate.parse(DateForInput, formatter);
            System.out.println("Введите конечную дату в формате: y-M-d, например 2024-2-21");
            DateForInput = scanner.next();
            LocalDate endDate = LocalDate.parse(DateForInput, formatter);
            FindTransactions(startDate, endDate);
        } catch (DateTimeParseException e2){
            System.out.println("Неверный формат ввода");
        }

    }
    }
