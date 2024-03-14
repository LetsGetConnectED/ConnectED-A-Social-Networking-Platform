package com.example.MessageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Message.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);
    
    List<Message> findByRecipient(String recipient);
    
    List<Message> findBySenderAndRecipient(String sender, String recipient);
    
    
}

