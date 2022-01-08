package com.example.kotlintojavachat;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVM
{
    private String name;
    private URL avatarImageLink;
}