package com.braimi.hamza.digital.banking.mappers;

import com.braimi.hamza.digital.banking.dtos.AccountOperationDTO;
import com.braimi.hamza.digital.banking.dtos.CurrentBankAccountDTO;
import com.braimi.hamza.digital.banking.dtos.CustomerDTO;
import com.braimi.hamza.digital.banking.dtos.SavingBankAccountDTO;
import com.braimi.hamza.digital.banking.entities.AccountOperation;
import com.braimi.hamza.digital.banking.entities.CurrentAccount;
import com.braimi.hamza.digital.banking.entities.Customer;
import com.braimi.hamza.digital.banking.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImplementation {

    @Autowired
    BankAccountMapperImplementation bankAccountMapperImplementation;


    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(bankAccountMapperImplementation.fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType("saving account");
        return savingBankAccountDTO;
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO,savingAccount);
        savingAccount.setCustomer(bankAccountMapperImplementation.fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO,currentAccount);
        currentAccount.setCustomer(bankAccountMapperImplementation.fromCustomerDTO(currentAccountDTO.getCustomerDTO()));

        return  currentAccount;
    }
    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setType("Current account");

        currentBankAccountDTO.setCustomerDTO(bankAccountMapperImplementation.fromCustomer(currentAccount.getCustomer()));
        return currentBankAccountDTO;
    }
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        accountOperationDTO.setAccountId(accountOperation.getBankAccount().getId());
        return  accountOperationDTO;
    }


}
