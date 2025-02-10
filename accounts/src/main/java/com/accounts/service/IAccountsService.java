package com.accounts.service;

import com.accounts.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface IAccountsService {

    /**
     * Create account
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
