package ru.netology.mballod;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CustomerService implements Serializable {
    private int nOfCustomers;
    private StorageService<Customer> customers = new StorageService<Customer>();

    public CustomerService(int nOfCustomers){
        this.nOfCustomers = nOfCustomers;
    }
    public void setCustomers(int customerId, Customer customer){
        if (customerId > nOfCustomers-1 || customerId<0)
            throw new CustomerOperationOutOfBoundException(customerId+1, 0);
        customers.add(customerId, customer);
    }

}
