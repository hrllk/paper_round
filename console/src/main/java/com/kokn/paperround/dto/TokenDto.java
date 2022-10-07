package com.kokn.paperround.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresln;
    private Long refreshTokenExpiresln;

    /***
     * 관리자 콘솔 접속아이디
     */
    private String id;

}
