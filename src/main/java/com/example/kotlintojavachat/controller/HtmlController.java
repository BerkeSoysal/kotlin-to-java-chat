package com.example.kotlintojavachat.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.kotlintojavachat.MessageVM;
import com.example.kotlintojavachat.service.MessageService;

@Controller
public class HtmlController
{
    private final MessageService messageService;

    @Autowired
    public HtmlController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        List<MessageVM> messages =  messageService.latest();

        model.addAttribute("messages", messages);
        model.addAttribute("lastMessageId", !CollectionUtils.isEmpty(messages) ? messages.get(messages.size()-1).getId() : "");

        return "chat";
    }
}