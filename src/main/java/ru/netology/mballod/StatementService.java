package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Setter
@Getter
public class StatementService implements Serializable {
    private int nOfCustomers;
    private int nOfTransactions;
    int[][] statement = new int[nOfCustomers][];

    public StatementService(int nOfCustomers, int nOfTransactions){
        this.nOfCustomers = nOfCustomers;
        this.nOfTransactions = nOfTransactions;
        this.statement = new int[nOfCustomers][];
    }

    public void AddOpToStatement(int transaction_n) throws CustomerOperationOutOfBoundException { // to StatementService
        int client = IOService.ClientNumInput(transaction_n, nOfCustomers);
        this.statement[client] = StorageService.ArrayIntAppend(this.statement[client], transaction_n);
    }
}
