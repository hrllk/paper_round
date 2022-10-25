package com.kokn.paperround.service;

import com.kokn.paperround.repository.UserKeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeywordService {
    private final UserKeywordRepository userKeywordRepository;

    public List<String> getKeywordListByUserId(Long userId) {
        return userKeywordRepository.findByUserId(userId).stream()
                .map(k -> k.getKeyword())
                .collect(Collectors.toList());
    }

}
