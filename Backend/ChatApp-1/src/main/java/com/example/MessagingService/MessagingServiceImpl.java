package com.example.MessagingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Message.Message;
import com.example.Message.MessageRequest;
import com.example.Message.MessageResponse;
import com.example.MessageRepository.MessageRepository;
import com.example.MessagingService.MessagingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagingServiceImpl implements MessagingService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessagingServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {
        Message message = new Message(messageRequest.getSenderId(), messageRequest.getRecipientId(), messageRequest.getContent());
        messageRepository.save(message);
        return new MessageResponse(message.getId(), message.getSenderId(), message.getRecipientId(), message.getContent());
    }

    @Override
    public List<MessageResponse> getMessagesBySender(String sender) {
        List<Message> messages = messageRepository.findBySender(sender);
        return mapMessagesToResponses(messages);
    }

    @Override
    public List<MessageResponse> getMessagesByRecipient(String recipient) {
        List<Message> messages = messageRepository.findByRecipient(recipient);
        return mapMessagesToResponses(messages);
    }

    @Override
    public List<MessageResponse> getMessagesBySenderAndRecipient(String sender, String recipient) {
        List<Message> messages = messageRepository.findBySenderAndRecipient(sender, recipient);
        return mapMessagesToResponses(messages);
    }

    private List<MessageResponse> mapMessagesToResponses(List<Message> messages) {
        return messages.stream()
                .map(message -> new MessageResponse(message.getId(), message.getSenderId(), message.getRecipientId(), message.getContent()))
                .collect(Collectors.toList());
    }
}