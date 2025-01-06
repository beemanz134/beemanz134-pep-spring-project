package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existsById(Integer accountId) {
        return accountRepository.existsById(accountId);
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);

    }

    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
}
