package com.kokn.paperround.auth;

import com.kokn.paperround.advisor.ConflictException;
import com.kokn.paperround.component.EmailSender;
import com.kokn.paperround.component.TokenProvider;
import com.kokn.paperround.constants.Constants;
import com.kokn.paperround.dto.SignInDto;
import com.kokn.paperround.dto.SignUpDto;
import com.kokn.paperround.dto.TokenDto;
import com.kokn.paperround.entity.ConfirmationToken;
import com.kokn.paperround.entity.User;
import com.kokn.paperround.repository.ConfirmationTokenRepository;
import com.kokn.paperround.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService implements UserDetailsService {

    private final static String CONFIRM_URL = "/api/v1/confirmation?id=";
//    private final static String CONFIRM_PATH = "/confirmation?id=";
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final EmailSender emailSender;


    public void signup(SignUpDto dto) {


        if (isDuplicated(dto.getEmail())){
            throw new ConflictException("already exist user");
        }

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(dto, User.class);
        user.setPassword(encryptPassword(user.getPassword()));
        userRepository.save(user);

        /***
         * Confirmation Feature
         * 1. save confirmation token
         * 2. send token email
         */
        ConfirmationToken token = ConfirmationToken.createEmailConfirmationToken(dto.getEmail());
        confirmationTokenRepository.save(token);
        emailSender.sendSubscriptionMail(Constants.SIGNUP_CONFIRM_URL + CONFIRM_URL + token.getConfirmationTokenId(), dto.getEmail());

    }

    private boolean isDuplicated(String email){
        Optional<User> userData = Optional.ofNullable(userRepository.findByEmail(email));
        return userData.isPresent() ? true : false;
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


    public TokenDto signin(SignInDto dto){
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(dto.getEmail(), dto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.debug("authentication: [{}]", authentication);
        return tokenProvider.generateTokenDto(authentication);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n\n\n!!!!loadUserByUsername!!!!\n\n\n");
        log.debug("username: [{}]", username);
        User user = Optional.ofNullable(userRepository.findByEmail(username)).orElseThrow(() -> new UsernameNotFoundException("User Not Found Email Was Wrong"));

        log.debug("user: [{}]", user);
        return generateUserDetails(user);
    }

    private UserDetails generateUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority());
        return new UserPrincipalDetails(user.getEmail(), user.getPassword(), Collections.singleton(grantedAuthority));
    }
}
