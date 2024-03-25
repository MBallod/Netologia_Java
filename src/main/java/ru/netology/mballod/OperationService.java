package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OperationService implements Serializable {
    private int nOfTransactions;
    private List<Operation> operations = new ArrayList<>();

    public OperationService(int nOfTransactions){
        this.nOfTransactions = nOfTransactions;
    }
    public void addOperation(Operation operation){
        this.operations.add(operation);
    }
    public void AddOpToOperations(int transaction_n){ //ввод операции, добавляет операцию в список операций
        Operation newOperation = IOService.OperationInput();
        operations.add(transaction_n, newOperation);
    }
    public void FindTransactions(LocalDate startDate, LocalDate endDate){
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (Operation op : this.getOperations()){
            if (op == null || op.getDate() == null) continue;
            if (op.getDate().isAfter(startDate.minusDays(1)) && op.getDate().isBefore(endDate.plusDays(1))){
                op.print();
            }
        }
    }


}
