package com.example.controller;


import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * GET localhost:8080/accounts/9999/messages
 * getAllMessagesMessagesAvailable http://localhost:8080/messages
 * GET localhost:8080/messages/1  getmessagebyid
 * PATCH localhost:8080/messages/9999
 * POST localhost:8080/login
 * POST localhost:8080/register
 * controller -> service -> repository
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {

    private final MessageService messageService;
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable("accountId") Integer accountId) {
        List<Message> messages = messageService.getAllMessagesBy(accountId);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 if empty
        }
        return ResponseEntity.ok(messages);
    }

    @GetMapping("messages")
    public String getAllMessagesAvailable() {

        return null;
    }

    @GetMapping("messages/{messageId}")
    public String getMessageById(@PathVariable("messageId") Integer messageId) {

        return null;
    }





}
