package com.example.kotlintojavachat;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageVM
{
    private String content;
    private UserVM user;
    private Instant sent;
    private String id;
}
