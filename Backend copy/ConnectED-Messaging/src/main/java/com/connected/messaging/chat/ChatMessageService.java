package com.connected.messaging.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connected.messaging.chatroom.ChatRoomService;


@Service
public class ChatMessageService {
    
    @Autowired
    ChatMessageRepository repository;
    
    @Autowired
    ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        // Convert senderId and recipientId from Long to String
        String senderId = String.valueOf(chatMessage.getSenderId());
        String recipientId = String.valueOf(chatMessage.getRecipientId());
        
        var chatId = chatRoomService
                .getChatRoomId(senderId, recipientId, true)
                .orElseThrow(); // You can create your own dedicated exception
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
