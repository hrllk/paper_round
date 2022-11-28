package com.kokn.paperround.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String name;
    private String password;
}
