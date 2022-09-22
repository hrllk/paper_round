package com.kokn.paperround.component;

import com.kokn.paperround.auth.UserPrincipalDetails;
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
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {
    public static final String JWT_SECRET_KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";
    private static final String AUTHORITIES_KEY = "auth";
    private final Key key;

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
