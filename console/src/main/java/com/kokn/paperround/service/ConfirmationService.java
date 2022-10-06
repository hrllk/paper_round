package com.kokn.paperround.service;

import com.kokn.paperround.entity.ConfirmationToken;
import com.kokn.paperround.entity.User;
import com.kokn.paperround.repository.ConfirmationTokenRepository;
import com.kokn.paperround.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ConfirmationService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;


    public void confirmation(Long confirmId){


        /***
         * token does not exist
         */
        ConfirmationToken token = Optional.ofNullable(confirmationTokenRepository.findByConfirmationTokenId(confirmId)).orElseThrow(() -> new RuntimeException("token does not exist"));

        /***
         * token expired
         */
        long expiredTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (expiredTime < new Date().getTime()) {
            throw new RuntimeException("Expired Confirmation Token");
        }

        /***
         * success confirm
         */
        User user = userRepository.findByEmail(token.getUserId());
        user.setIsConfirm((byte) 1);
    }
}
