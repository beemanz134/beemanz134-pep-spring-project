package com.example.service;

import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existsById(Integer accountId) {
        return accountRepository.existsById(accountId);
    }

}
