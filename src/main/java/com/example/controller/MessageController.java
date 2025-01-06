package com.example.controller;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.badRequest().build();
        }
        if (m.getTimePostedEpoch() == null) {
            m.setTimePostedEpoch(System.currentTimeMillis() / 1000);
        }
        Message createdMessage = messageService.addMessage(m);
        return ResponseEntity.ok(createdMessage);
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable("messageId") int messageId) {
        if (!messageService.existsById(messageId)) {
            return ResponseEntity.ok().build();
        }
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok(1);
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable("messageId") int messageId, @RequestBody Message m) {
        if(!messageService.existsById(messageId) || m.getMessageText() == null || m.getMessageText().length() > 255 || m.getMessageText().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Message updateMessage = messageService.updateMessage(messageId, m);
        return ResponseEntity.ok(1);
    }
}
