package com.example.kotlintojavachat.service;

import java.util.List;
import com.example.kotlintojavachat.MessageVM;

public interface MessageService
{
    List<MessageVM> latest();
    List<MessageVM> after(String messageId);
    void post(MessageVM messageVM);
}