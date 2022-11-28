package com.kokn.paperround.auth;

import com.google.common.base.CharMatcher;
import com.kokn.paperround.advisor.CustomException;
import com.kokn.paperround.component.EmailSender;
import com.kokn.paperround.component.TokenProvider;
import com.kokn.paperround.constants.Constants;
import com.kokn.paperround.dto.SignInRequest;
import com.kokn.paperround.dto.SignInResponseDto;
import com.kokn.paperround.dto.SignUpRequest;
import com.kokn.paperround.entity.ConfirmationToken;
import com.kokn.paperround.entity.User;
import com.kokn.paperround.repository.ConfirmationTokenRepository;
import com.kokn.paperround.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static com.kokn.paperround.advisor.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService implements UserDetailsService {

//    private final static String CONFIRM_URI = "/auth/confirmation?id=";
    private final static String CONFIRM_URI = "/auth/confirmation";

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final EmailSender emailSender;


    public void signup(SignUpRequest dto) {


        String email = dto.getEmail();
        duplicateCheck(email);

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dto, User.class);
        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.save(user);

        /***
         * Confirmation Feature
         * 1. save confirmation token
         * 2. send token email
         */
        ConfirmationToken token = ConfirmationToken.createEmailConfirmationToken(email);
        confirmationTokenRepository.save(token);
        long tokenId = token.getConfirmationTokenId();
        String link = generateSignUpLink(tokenId);

        emailSender.sendSubscriptionMail(link, email);
    }

    private void duplicateCheck(String email) {
        if (Optional.ofNullable(userRepository.findByEmail(email)).isPresent())
            throw new CustomException(CONFLICT);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private String generateSignUpLink(long tokenId){
//        return String.format("%s%s%s", Constants.HOST_URL, CONFIRM_URI, tokenId);
//        String url = Constants.HOST_URL + CONFIRM_URI + tokenId;
        return String.format("%s%s/%s", Constants.HOST_URL, CONFIRM_URI, tokenId);
//        log.debug("url: [{}]", url);
//        return url;
    }

















    public SignInResponseDto signIn2(SignInRequest dto){
        // 토큰생성 추후에 검증하기위함.
        UsernamePasswordAuthenticationToken unAuthenticatedToken = UsernamePasswordAuthenticationToken.unauthenticated(dto.getEmail(), dto.getPassword());

        // 위에서 생성한 토큰을이용해 검증시도,
        log.debug("try authentication by authenticationToken");
        AuthenticationManager manager = authenticationManagerBuilder.getObject();
        Authentication authentication = manager.authenticate(unAuthenticatedToken);
        String email = authentication.getName();
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (user.getIsConfirm() == 0)
            throw new CustomException(USER_NOT_CONFIRM);

        String accessToken = tokenProvider.generateAccessToken(authentication, user);

        return SignInResponseDto.builder()
                .userId(user.getUserId())
                .accessToken(accessToken)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("trying authentication in loadUserByUsername");
        User user = Optional.ofNullable(userRepository.findByEmail(username)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        return generateUserDetails(user);
    }

    private UserDetails generateUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority());
        return new UserPrincipalDetails(user.getEmail(), user.getPassword(), Collections.singleton(grantedAuthority));
    }

    public void verify(HttpHeaders headers, Long userId){

        String accessToken = CharMatcher.anyOf("[]").removeFrom(String.valueOf(headers.get("Authorization")));
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String email = authentication.getName();

        Optional<User> user = userRepository.findById(userId);

        if (!StringUtils.equals(user.get().getEmail(),email))
            throw new CustomException(BAD_REQUEST);
    }
}
