package ru.netology.mballod;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Main {
    public static final int N_OF_TRANSACTIONS = 2;
    public static final int N_OF_CUSTOMERS = 2;
    public static Operation[] operations = new Operation[N_OF_TRANSACTIONS];
    public static Customer[] customers = new Customer[N_OF_CUSTOMERS];
    public static int[][] statement = new int[N_OF_CUSTOMERS][];
    public static void FindTransactions(LocalDate startDate, LocalDate endDate){
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (Operation op : operations){
            if (op == null || op.getDate() == null) continue;
            if (op.getDate().isAfter(startDate.minusDays(1)) && op.getDate().isBefore(endDate.plusDays(1))){
                op.print();
            }
        }
    }
    public static Operation[] getOperations(int clientId){
        Operation[] result = new Operation[0];
        for (int opId : statement[clientId]) {
            result = ArrayOpAppend(result, operations[opId]);
        }
        return result;
    }
    public static void printOperations(int clientId){
        System.out.println("Операции клиента: " + (clientId+1));
        for (Operation op: getOperations(clientId)){
            op.print();
        }
    }
    public static int[] ArrayIntAppend(int[] arr, int a){
        int[] array;
        if (arr == null){
            array= new int[1];
            array[0] =a;
        }else {
            array = Arrays.copyOf(arr, arr.length+1);
            array[array.length-1] = a;
        }
        return array;
    }
    public static Operation[] ArrayOpAppend(Operation[] arr, Operation a){
        Operation[] array;
        if (arr == null){
            array= new Operation[1];
            array[0] =a;
        }else {
            array = Arrays.copyOf(arr, arr.length+1);
            array[array.length-1] = a;
        }
        return array;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Общее число клиентов: "+ N_OF_CUSTOMERS);
        System.out.println("Общее число транзакций: "+ N_OF_TRANSACTIONS);

        for(int client = 0; client<N_OF_CUSTOMERS;client++) {
            Customer customer = new Customer();
            System.out.println("Введите информацию о кллиенте номер " + (client + 1));
            try {
                System.out.println("Введите полное имя клиента: ");
                customer.setName(scanner.next());
                System.out.println("Введите сумму на счете клиента");
                customer.setWealth(scanner.nextDouble());
                System.out.println("Введите возраст клиента");
                customer.setAge(scanner.nextInt());
                customers[client] = customer;
            } catch (InputMismatchException | DateTimeParseException e1) {
                System.out.println("Неверный формат ввода");
            }
        }
        for (int transaction = 0; transaction < N_OF_TRANSACTIONS; transaction++) {
            Operation operation = new Operation();
            System.out.println("Введите информацию о транзакции номер " + (transaction + 1));
            try {
                System.out.println("Введите номер клиента - от 1 до " + N_OF_CUSTOMERS);
                int client = scanner.nextInt() - 1;
                System.out.println("Введите дату транзакции в формате: " + Operation.pattern + ", например 2024-2-21");
                String DateForInput = scanner.next();
                operation.setDate(LocalDate.parse(DateForInput, Operation.formatter));
                System.out.println("Введите сумму транзакции в рублях, разделяя точкой целую и дробную часть");
                operation.setAmount(scanner.nextDouble());
                System.out.println("Транзакция входящая (y) или исходящая (n)?");
                operation.setDebitFromString(scanner.next());
                operations[transaction] = operation;

                statement[client] = ArrayIntAppend(statement[client], transaction);
                operations[transaction].print();

            } catch (InputMismatchException | DateTimeParseException | ArrayIndexOutOfBoundsException e1) {
                System.out.println("Неверный формат ввода");
                }
            }
        /*
            //вывод информации о всех корректно записанных транзакциях
        for (int OperationId = 0; OperationId < N_OF_TRANSACTIONS; OperationId++) {
                if (operations[OperationId] == null) continue;
                System.out.print("Транзакция - " + OperationId + ": ");
                operations[OperationId].print();
        }
        */
        System.out.println("Вывод информации об операциях клиента. Введите номер клиента");
        int clientId = scanner.nextInt()-1;
        customers[clientId].print();
        printOperations(clientId);


/*
        System.out.println("А сейчас найдем транзакции за заданный период");
        try {
            System.out.println("Введите начальную дату в формате: "+ Operation.pattern+ ", например 2024-2-21");
            LocalDate startDate = LocalDate.parse(scanner.next(), Operation.formatter);
            System.out.println("Введите конечную дату в формате: "+ Operation.pattern+ ", например 2024-2-21");
            LocalDate endDate = LocalDate.parse(scanner.next(), Operation.formatter);
            FindTransactions(startDate, endDate);
        } catch (DateTimeParseException e2){
            System.out.println("Неверный формат ввода");
        }
*/
    }
}
