package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class OperationService implements Serializable {
    private int nOfTransactions;
    private StorageService<Operation> operations = new StorageService<Operation>(); //Operation[] operations = new Operation[nOfTransactions];

    public OperationService(int nOfTransactions){
        this.nOfTransactions = nOfTransactions;
        //this.operations = new Operation[nOfTransactions];
    }

    public void AddOpToOperations(int transaction_n){ //добавляет операцию в список операций
        Operation newOperation = IOService.OperationInput();
        operations.add(transaction_n, newOperation);
    }
    public void FindTransactions(LocalDate startDate, LocalDate endDate){ // to OperationService
        //выводит инфомацию о транзакциях между заданными датами, включая сами даты
        for (Operation op : this.getOperations().values){
            if (op == null || op.getDate() == null) continue;
            if (op.getDate().isAfter(startDate.minusDays(1)) && op.getDate().isBefore(endDate.plusDays(1))){
                op.print();
            }
        }
    }
}
