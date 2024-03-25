package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Setter
@Getter
public class StatementService implements Serializable {
    private int nOfCustomers;
    private int nOfTransactions;
    private Map<Integer, List<Operation>> storage = new HashMap<>();

    public StatementService(int nOfCustomers, int nOfTransactions){
        this.nOfCustomers = nOfCustomers;
        this.nOfTransactions = nOfTransactions;
    }

    public void AddOpToStatement(Customer customer, Operation operation){ // to StatementService
        if(storage.containsKey(customer.getId())){
            storage.get(customer.getId()).add(operation);
        }else {
            List<Operation> oneOp = new ArrayList<>(Arrays.asList(operation));
            storage.put(customer.getId(), oneOp);
        }

    }
}
