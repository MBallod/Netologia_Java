package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Setter
@Getter
public class OperationData implements Serializable {

    public static final int N_OF_TRANSACTIONS = 2;
    public static final int N_OF_CUSTOMERS = 2;
    private Operation[] operations = new Operation[N_OF_TRANSACTIONS];
    private Customer[] customers = new Customer[N_OF_CUSTOMERS];
    private int[][] statement = new int[N_OF_CUSTOMERS][];

    public void setCustomers(int customerId, Customer customer){
        this.customers[customerId] = customer;
    }

    public void FindTransactions(LocalDate startDate, LocalDate endDate){
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (Operation op : this.operations){
            if (op == null || op.getDate() == null) continue;
            if (op.getDate().isAfter(startDate.minusDays(1)) && op.getDate().isBefore(endDate.plusDays(1))){
                op.print();
            }
        }
    }
    public Operation[] getOperations(int clientId){
        Operation[] result = new Operation[0];
        for (int opId : this.statement[clientId]) {
            result = ArrayOpAppend(result, operations[opId]);
        }
        return result;
    }
    public void printOperations(int clientId){
        System.out.println("Операции клиента: " + (clientId+1));
        for (Operation op: getOperations(clientId)){
            op.print();
        }
    }
    private int[] ArrayIntAppend(int[] arr, int a){
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
    public Operation[] ArrayOpAppend(Operation[] arr, Operation a){
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
    public void AddOpToStatement(int transaction_n) throws CustomerOperationOutOfBoundException {
        Scanner scanner = new Scanner(System.in);
        Operation operation = new Operation();
        try {
            System.out.println("Введите номер клиента - от 1 до " + N_OF_CUSTOMERS);
            int client = scanner.nextInt() - 1;
            if (client>N_OF_CUSTOMERS-1 || client<0)
                throw new CustomerOperationOutOfBoundException(client+1, transaction_n+1);
            System.out.println("Введите дату транзакции в формате: " + Operation.pattern + ", например 2024-2-21");
            String DateForInput = scanner.next();
            operation.setDate(LocalDate.parse(DateForInput, Operation.formatter));
            System.out.println("Введите сумму транзакции в рублях, разделяя точкой целую и дробную часть");
            operation.setAmount(scanner.nextDouble());
            System.out.println("Транзакция входящая (y) или исходящая (n)?");
            operation.setDebitFromString(scanner.next());
            this.operations[transaction_n] = operation;

            this.statement[client] = ArrayIntAppend(this.statement[client], transaction_n);
            this.operations[transaction_n].print();

        } catch (InputMismatchException | DateTimeParseException e1) {
            System.out.println("Неверный формат ввода");
        }
    }


}
