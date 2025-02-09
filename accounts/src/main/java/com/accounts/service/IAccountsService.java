package com.accounts.service;

import com.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create account
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}
