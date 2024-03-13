package com.example.MessageController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Message.MessageRequest;
import com.example.Message.MessageResponse;
import com.example.MessagingService.MessagingService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessagingService messagingService;

    @Autowired
    public MessageController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @PostMapping("/send")
    public MessageResponse sendMessage(@RequestBody MessageRequest messageRequest) {
        return messagingService.sendMessage(messageRequest);
    }

    @GetMapping("/sender/{sender}")
    public List<MessageResponse> getMessagesBySender(@PathVariable String sender) {
        return messagingService.getMessagesBySender(sender);
    }

    @GetMapping("/recipient/{recipient}")
    public List<MessageResponse> getMessagesByRecipient(@PathVariable String recipient) {
        return messagingService.getMessagesByRecipient(recipient);
    }

    @GetMapping("/sender/{sender}/recipient/{recipient}")
    public List<MessageResponse> getMessagesBySenderAndRecipient(@PathVariable String sender, @PathVariable String recipient) {
        return messagingService.getMessagesBySenderAndRecipient(sender, recipient);
    }
}
