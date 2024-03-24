package ru.netology.mballod;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Ввод данных о клиентах и операциях
        OperationData operationData = new OperationData();
        System.out.println("Общее число клиентов: "+ OperationData.N_OF_CUSTOMERS);
        System.out.println("Общее число транзакций: "+ OperationData.N_OF_TRANSACTIONS);

        for(int client = 0; client< OperationData.N_OF_CUSTOMERS; client++) { // to CustomerService
            System.out.println("Введите информацию о клиенте номер " + (client + 1));
            Customer customer = IOService.CustomerInput();
            operationData.getCustomers().setCustomers(client, customer);
        }
        for (int transaction = 0; transaction < OperationData.N_OF_TRANSACTIONS; transaction++) { // to StatementService
            System.out.println("Введите информацию о транзакции номер " + (transaction + 1));
            operationData.getOperations().AddOpToOperations(transaction);
            operationData.getStatement().AddOpToStatement(transaction);
            }
        IOService.printOperations(operationData);

        IOService.putOperationDataInFile(operationData);
        OperationData operationsDataFromFile = IOService.takeOperationDataFromFile();
        //Проверим, корректны ли данные о клиенте и его транзакиях, полученные из файла
        IOService.printOperations(operationsDataFromFile);
    }
}
