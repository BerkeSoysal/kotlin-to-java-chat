package com.example.kotlintojavachat.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.kotlintojavachat.MessageVM;
import com.example.kotlintojavachat.service.MessageService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageResource
{
    private MessageService messageService;

    @Autowired
    private MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageVM>> latest(@RequestParam(value = "lastMessageId", defaultValue = "") String lastMessageId){
        List<MessageVM> messageVMS;
        if(StringUtils.isNotEmpty(lastMessageId))
        {
            messageVMS = messageService.after(lastMessageId);
        }
        else
        {
            messageVMS = messageService.latest();
        }

        if(messageVMS.isEmpty())
        {
            return ResponseEntity.noContent()
                    .header("lastMessageId", lastMessageId)
                    .build();
        }
        else {
            return ResponseEntity.ok()
                    .header("lastMessageId", lastMessageId)
                    .body(messageVMS);
        }
    }
}