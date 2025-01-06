package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public AccountController(AccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        Optional<Account> accountOptional = accountService.findByUsername(loginRequest.getUsername());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(account); // 200 OK
            }
        }
        return ResponseEntity.status(401).build(); // 401 Unauthorized
    }

    @PostMapping("register")
    public String register() {
        return null;
    }
}
