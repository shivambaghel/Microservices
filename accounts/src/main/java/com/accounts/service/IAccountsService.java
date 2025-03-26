package com.accounts.service;

import com.accounts.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
