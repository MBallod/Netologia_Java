package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CustomerService implements Serializable {
    private int nOfCustomers;
    private List<Customer> customers = new ArrayList<>();

    public CustomerService(int nOfCustomers){
        this.nOfCustomers = nOfCustomers;
    }
    public void setCustomers(int customerId, Customer customer){
        if (customerId > nOfCustomers-1 || customerId<0)
            throw new CustomerOperationOutOfBoundException(customerId+1);
        customers.add(customerId, customer);
    }
    public Customer findCustomerById (int Id){ // не проверяете клиентов на совпадение Id
        for (Customer client : customers){
            if (Id == client.getId()){
                return client;
            }
        }
        return null;
    }


}
