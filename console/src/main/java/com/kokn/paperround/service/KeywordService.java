package com.kokn.paperround.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kokn.paperround.advisor.CustomException;
import com.kokn.paperround.dto.UserKeywordRequest;
import com.kokn.paperround.entity.Keyword;
import com.kokn.paperround.entity.UserKeyword;
import com.kokn.paperround.repository.KeywordRepository;
import com.kokn.paperround.repository.UserKeywordRepository;
import com.kokn.paperround.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kokn.paperround.advisor.ErrorCode.UNOFFICIAL_WORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeywordService {
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;

    public List<String> getKeywordListByUserId(Long userId) {
        return userKeywordRepository.findByUserId(userId).stream()
                .map(k -> k.getKeyword())
                .collect(Collectors.toList());
    }


    public void register(UserKeywordRequest dto) {
        log.debug("register dto: [{}]", dto);

        Long userId = dto.getUserId();
        String keyword = dto.getKeyword();


        Long keywordId = upsertKeywordMaster(keyword);

        if (checkDuplicate(userId, keyword))
            return;

        // insert
        ModelMapper modelMapper = new ModelMapper();
        UserKeyword newKeyword = modelMapper.map(dto, UserKeyword.class);
        newKeyword.setKeywordId(keywordId);
        userKeywordRepository.save(newKeyword);

    }

    private Long upsertKeywordMaster(String keyword) {
//    private Long updateKeywordMaster(String keyword) {
        Optional<Keyword> keywordData = keywordRepository.findByKeyword(keyword);

        // update
        if (keywordData.isPresent()) {
            Keyword keywordMaster = keywordData.get();
            keywordMaster.setUseCnt(keywordMaster.getUseCnt() + 1);
            return keywordMaster.getKeywordId();
        }

        verifyOfficialWord(keyword);

        Keyword newKeywordMaster = new Keyword();
        newKeywordMaster.setKeyword(keyword);
        keywordRepository.save(newKeywordMaster);
        return newKeywordMaster.getKeywordId();

    }


    public void verifyOfficialWord(String keyword) {
        /***
         * 네이버사전 API
         */
        Map header = new HashMap();
        header.put("X-Naver-Client-Id", "qXPada7nrmo0GeQ1aio9");
        header.put("X-Naver-Client-Secret", "zT2QlFbooc");
        String url = "https://openapi.naver.com/v1/search/encyc.json?query=" + keyword + "&display=10&start=1&sort=sim";

        Response response = HttpUtils.getResponse(url, header);
        String responseStr = null;

        try {
            responseStr = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonObject responseObj = new Gson().fromJson(responseStr, JsonObject.class);
        JsonElement total = responseObj.get("total");

        if (1 >= total.getAsInt())
            throw new CustomException(UNOFFICIAL_WORD) ;
    }


    private boolean checkDuplicate(Long userId, String keyword) {
        Optional<UserKeyword> userKeywordData = userKeywordRepository.findByUserIdAndKeyword(userId, keyword);
        return userKeywordData.isPresent() ?  true : false;
    }


    private void synchronizeKeywordMaster(String keyword) {
        Optional<Keyword> keywordData = keywordRepository.findByKeyword(keyword);

        // insert
        if (!keywordData.isPresent()) {
            Keyword keywordMaster = new Keyword();
            keywordMaster.setKeyword(keyword);
            keywordRepository.save(keywordMaster);
            return;
        }

//        // update
//        Keyword keywordMaster = keywordData.get();
//        keywordMaster.setUseCnt(keywordMaster.getUseCnt() + 1);

    }

    @Transactional
//    public void remove(Long userId, String keyword) {
    public void remove(UserKeywordRequest dto) {

        Long userId = dto.getUserId();
        String keyword = dto.getKeyword();

        // 마스터에서 cnt --
        Optional<Keyword> keywordData = keywordRepository.findByKeyword(keyword);
        if (keywordData.isPresent()) {
            Keyword keywordMaster = keywordData.get();
            keywordMaster.setUseCnt(keywordMaster.getUseCnt() - 1);
        }

        userKeywordRepository.deleteByUserIdAndKeyword(userId, keyword);


    }



}
