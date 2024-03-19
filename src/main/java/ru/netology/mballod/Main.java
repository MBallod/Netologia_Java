package ru.netology.mballod;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Ввод данных о клиентах и операциях
        Scanner scanner = new Scanner(System.in);
        OperationData operationData = new OperationData();
        System.out.println("Общее число клиентов: "+ OperationData.N_OF_CUSTOMERS);
        System.out.println("Общее число транзакций: "+ OperationData.N_OF_TRANSACTIONS);

        for(int client = 0; client< OperationData.N_OF_CUSTOMERS; client++) {
            Customer customer = new Customer();
            System.out.println("Введите информацию о клиенте номер " + (client + 1));
            try {
                System.out.println("Введите полное имя клиента: ");
                customer.setName(scanner.next());
                System.out.println("Введите сумму на счете клиента");
                customer.setWealth(scanner.nextDouble());
                System.out.println("Введите возраст клиента");
                customer.setAge(scanner.nextInt());
                operationData.setCustomers(client, customer);
            } catch (InputMismatchException | DateTimeParseException e1) {
                System.out.println("Неверный формат ввода");
            }
        }
        for (int transaction = 0; transaction < OperationData.N_OF_TRANSACTIONS; transaction++) {
            System.out.println("Введите информацию о транзакции номер " + (transaction + 1));
            operationData.AddOpToStatement(transaction);
            }

        //Сериализация полученных данных
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(operationData);
        oos.flush();
        oos.close();
        //Достанем данные из файла
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        OperationData operationsDataFromFile = (OperationData) oin.readObject();

        //Проверим, корректны ли данные о клиенте и его транзакиях, полученные из файла
        System.out.println("Вывод информации об операциях клиента. Введите номер клиента");
        int clientId = scanner.nextInt()-1;
        if (clientId> OperationData.N_OF_CUSTOMERS-1 || clientId<0)
            throw new CustomerOperationOutOfBoundException(clientId+1, 0); //реальной транзакции нет, но номер клиента неверный
        operationsDataFromFile.getCustomers()[clientId].print();
        operationsDataFromFile.printOperations(clientId);

    }
}
