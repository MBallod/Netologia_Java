package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class OperationData implements Serializable {
    public static final int N_OF_CUSTOMERS = 2;
    public static final int N_OF_TRANSACTIONS = 2;
    private CustomerService customers = new CustomerService(N_OF_CUSTOMERS);
    private OperationService operations = new OperationService(N_OF_TRANSACTIONS);
    private StatementService statement = new StatementService(N_OF_CUSTOMERS, N_OF_TRANSACTIONS);

    public OperationService getOperations(int clientId){ // Все операции клиента из statement
        OperationService result = new OperationService(0);
        for (int opId : this.statement.getStatement()[clientId]) {
            result.getOperations().add(this.operations.getOperations().values.get(opId));
        }
        return result;
    }

}
