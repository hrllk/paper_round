package com.kokn.paperround.service;

import com.kokn.paperround.entity.User;
import com.kokn.paperround.entity.UserKeyword;
import com.kokn.paperround.repository.UserKeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeywordService {

//    private final KeywordRepository keywordRepository;
    private final UserKeywordRepository userKeywordRepository;

    public String getKeywordByUserId(Long userId) {
        List<UserKeyword> keywords = userKeywordRepository.findByUserId(userId);
        log.debug("keywords: [{}]", keywords);
        return null;
    }
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
