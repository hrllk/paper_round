package com.kokn.paperround.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokn.paperround.advisor.CustomException;
import com.kokn.paperround.advisor.ErrorCode;
import com.kokn.paperround.auth.UserPrincipalDetails;
import com.kokn.paperround.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

import static com.kokn.paperround.advisor.ErrorCode.UNAUTHORIZED_TOKEN;

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

    public boolean isValidate(String token) throws ExpiredJwtException {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        log.debug("Valid Token: [{}]", token);
        return true;
    }







    public String generateAccessToken(Authentication authentication, User user){
        Date expireDt = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME);

//        String authorities = parseAuthorities(authentication);

        return Jwts.builder()

                .setSubject(authentication.getName())       // id를지정하고,
//                .claim(AUTHORITIES_KEY, authorities)        // key는 사용자가 가지고있는 권한을 사용하며,
                .claim("user", user)        // key는 사용자가 가지고있는 권한을 사용하며,
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

        verify(token);

        Claims claims = parseClaims(token);
        ObjectMapper om = new ObjectMapper();
        User user = om.convertValue(claims.get("user"), User.class);
        log.debug("claims.get(AUTHORITIES_KEY): [{}]", claims.get(AUTHORITIES_KEY));
        String authorities = user.getAuthority();

        Collection<? extends GrantedAuthority> authList = Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails principal = new UserPrincipalDetails(claims.getSubject(), "", authList);
        return new UsernamePasswordAuthenticationToken(principal, "", authList);
    }

    private void verify(String token){
        if(StringUtils.isEmpty(token))
            throw new CustomException(UNAUTHORIZED_TOKEN);
    }

    private Claims parseClaims(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        return parser.parseClaimsJws(token).getBody();
    }
}
