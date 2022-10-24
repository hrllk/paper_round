package com.kokn.paperround.component;

import com.kokn.paperround.auth.UserPrincipalDetails;
import com.kokn.paperround.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {
    public static final String JWT_SECRET_KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";
    private static final String AUTHORITIES_KEY = "auth";
    private final Key key;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
    private static final String BEARER_TYPE = "bearer";

    /***
     * init
     */
    public TokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) throws ExpiredJwtException {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            log.debug("Valid Token: [{}]", token);
            return true;
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            log.error("Invalid Token Cause: [{}]", e);
        }
        return false;
    }

//    public TokenDto generateTokenDto(Authentication authentication){
//    public String generateTokenDto(Authentication authentication){
//        Date expireDt = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME);
//        return generateAccessToken(authentication, expireDt);
////        String accessToken = generateAccessToken(authentication, expireDt);
////
////        return TokenDto.builder()
//////                .grantType(BEARER_TYPE)
////                .accessToken(accessToken)
////                .accessTokenExpiresln(expireDt.getTime())
////                .build();
//    }



    public String generateAccessToken(Authentication authentication){
        Date expireDt = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        String authorities = parseAuthorities(authentication);

        return Jwts.builder()
                .setSubject(authentication.getName())       // id를지정하고,
                .claim(AUTHORITIES_KEY, authorities)        // key는 사용자가 가지고있는 권한을 사용하며,
                .setExpiration(expireDt)                    // 만료시간을 설정하고,
                .signWith(key, SignatureAlgorithm.HS512)    // 서명(고유)값과, 해시키를 지정하여
                .compact();                                 // 만든다(토큰을)

    }

    private String generateAccessToken(Authentication authentication, Date expireDt){

        String authorities = parseAuthorities(authentication);

        return Jwts.builder()
                .setSubject(authentication.getName())       // id를지정하고,
                .claim(AUTHORITIES_KEY, authorities)        // key는 사용자가 가지고있는 권한을 사용하며,
                .setExpiration(expireDt)                    // 만료시간을 설정하고,
                .signWith(key, SignatureAlgorithm.HS512)    // 서명(고유)값과, 해시키를 지정하여
                .compact();                                 // 만든다(토큰을)

    }

    private String parseAuthorities(Authentication authentication){
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }


    /***
     * Check Authentication & Authority
     */
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        UserDetails principal = new UserPrincipalDetails(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        return parser.parseClaimsJws(token).getBody();
    }
}
