package com.connected.appchatmicro.controller;

import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connected.appchatmicro.dto.MessageRequest;
import com.connected.appchatmicro.dto.MessageResponse;
import com.connected.appchatmicro.service.MessageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessagesController {
    private final MessageService messagesService;
    
    public MessagesController(MessageService messagesService) {
        this.messagesService = messagesService;
    }


    @PostMapping("/send_message/")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest msgRequest) {
        messagesService.sendMessage(msgRequest.getSender_username()
                , msgRequest.getReceiver_username(), msgRequest.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/get_conversation/")
    public ResponseEntity<List<MessageResponse>> getConversation(@RequestBody MessageRequest msgRequest) {
        return status(HttpStatus.OK).body(messagesService.getConversation(msgRequest.getSender_username(), msgRequest.getReceiver_username()));
    }

    @PostMapping("/more_messages/")
    public ResponseEntity<List<MessageResponse>> more_messages(@RequestBody MessageResponse msgResponse) {
        return status(HttpStatus.OK).body(messagesService.loadMoreMessages(msgResponse));
    }



//    @GetMapping("/get_conversation_names/")
//    public ResponseEntity<List<User>> get_conversation_names() {
//        List<User> ulist = messagesService.get_conversation_names();
//        return status(HttpStatus.OK).body(ulist);
//    }



}
