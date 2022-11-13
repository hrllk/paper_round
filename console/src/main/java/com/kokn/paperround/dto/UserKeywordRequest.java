package com.kokn.paperround.dto;

import lombok.Data;

@Data
public class UserKeywordRequest {

//    private String accessToken;
    private Long userId;
    private String keyword;

}
