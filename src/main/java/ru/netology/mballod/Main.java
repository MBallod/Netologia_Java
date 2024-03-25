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
            customer.setId(client);
            operationData.getCustomers().setCustomers(client, customer);
        }
        for (int transaction = 0; transaction < OperationData.N_OF_TRANSACTIONS; transaction++) { // to StatementService
            System.out.println("Введите информацию о транзакции номер " + (transaction + 1));
            Operation operation = IOService.OperationInput();
            operation.setId(transaction);
            operationData.getOperations().addOperation(operation);
            Customer customer = IOService.whoIsClient(operationData); //важен порядок ввода - сначала клиенты, потом операции. whoIsClient нужен заполненый список клиентов
            operationData.getStatement().AddOpToStatement(customer, operation);
            }

        IOService.putOperationDataInFile(operationData);
        OperationData operationsDataFromFile = IOService.takeOperationDataFromFile();
        //Проверим, корректны ли данные о клиенте и его транзакиях, полученные из файла
        IOService.printOperations(operationsDataFromFile);
        //Зачем в программе обрабатывать пустую очередь - непонятно
        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(operationData.getOperations());
        asyncInputOperationService.startAsyncOperationProcessing();
    }
}
