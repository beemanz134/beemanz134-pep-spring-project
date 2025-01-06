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

    public Message deleteMessage(int messageId) {
        if (messageRepository.existsById(messageId)) {
            Message message = messageRepository.findById(messageId).orElse(null);
            messageRepository.deleteById(messageId);
            return message;
        }
        return null;
    }
    public boolean existsById(int messageId) {
        return messageRepository.existsById(messageId);
    }

    public List<Message> getAllMessagesBy(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        return message;
    }

    public Message updateMessage(int messageId, Message m) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message != null) {
            message.setMessageText(m.getMessageText());
            return messageRepository.save(message);
        }
        return null;
    }

}
