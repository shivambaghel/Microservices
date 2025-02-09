package com.accounts.service.impl;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.CustomerDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import com.accounts.exception.CustomerAlreadyExistsException;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        // findBy -> select query
        // findBy*arg1*And*arg2*( arg1, arg2) -> select query -> multiple parameters
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()) { // If customer already exists
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }

        Customer savedCustomer =customerRepository.save(customer);  // Save customer first to get customerId
        accountsRepository.save(createNewAccount(savedCustomer)); // Create new account for the customer
    }

    private Accounts createNewAccount(Customer customer) {  // Create new account for the customer
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000); // Generate random account number

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

}
