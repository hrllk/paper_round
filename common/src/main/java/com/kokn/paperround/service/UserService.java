package com.kokn.paperround.service;

import com.kokn.paperround.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

//    private final UserRepository userRepository;

    public List<User> list(){
        try {
//            return userRepository.findAll();
            return null;
        } catch(Exception e){
//            log.error("ERROR", e);
            return null;
        }
    }

}
