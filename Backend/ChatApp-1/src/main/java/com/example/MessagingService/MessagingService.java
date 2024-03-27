package com.example.MessagingService;

import java.util.List;
import com.example.Message.MessageRequest;
import com.example.Message.MessageResponse;

public interface MessagingService {
    MessageResponse sendMessage(MessageRequest messageRequest);
    
    List<MessageResponse> getMessagesBySender(String sender);
    
    List<MessageResponse> getMessagesByRecipient(String recipient);
    
    List<MessageResponse> getMessagesBySenderAndRecipient(String sender, String recipient);
}
