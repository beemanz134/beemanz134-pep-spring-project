package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message addMessage(Message m) {
        return messageRepository.save(m);
    }

    public List<Message> getAllMessagesBy(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }

    public String getAllMessages() {
        return null;
    }

    public String getMessageById(int messageId) {
        return null;
    }

}
