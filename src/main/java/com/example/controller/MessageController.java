package com.example.controller;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// * POST localhost:8080/messages
// * DELETE localhost:8080/messages/1

@RestController
@RequestMapping("/")
public class MessageController {

    private final MessageService messageService;
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageService messageService, AccountService accountService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }


    @PostMapping("messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message m) {
        if (m.getMessageText() == null || m.getMessageText().isEmpty() || m.getMessageText().length() > 255) {
            return ResponseEntity.badRequest().build(); // 400 if message text is invalid
        }
        if (m.getPostedBy() == null || !accountService.existsById(m.getPostedBy())) {
            return ResponseEntity.badRequest().build(); // 400 if postedBy is invalid
        }
        if (m.getTimePostedEpoch() == null) {
            m.setTimePostedEpoch(System.currentTimeMillis() / 1000); // Set to current epoch time in seconds
        }
        Message createdMessage = messageService.addMessage(m);
        return ResponseEntity.ok(createdMessage); // Return the created message with 200 OK
    }

    @DeleteMapping("messages/{messageId}")
    public String deleteMessage() {
        return null;
    }

    @PatchMapping("messages/{messageId}")
    public String updateMessage() {
        return null;
    }
}
