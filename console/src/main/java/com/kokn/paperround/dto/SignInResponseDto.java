package com.kokn.paperround.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponseDto {

    private String accessToken;

    private Long userId;

    /***
     * 관리자 콘솔 접속아이디
     */
//    private String id;

}
