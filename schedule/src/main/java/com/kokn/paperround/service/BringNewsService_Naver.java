package com.kokn.paperround.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokn.paperround.entity.News;
import com.kokn.paperround.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BringNewsService_Naver {

//    public List<News> getNews(String keyword) {
    public List<News> getNews(String keyword, Long keywordId, Long agencyId) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Naver-Client-Id", "qXPada7nrmo0GeQ1aio9");
        headers.put("X-Naver-Client-Secret", "zT2QlFbooc");

        String url = "https://openapi.naver.com/v1/search/news.json?query=" + keyword + "&display=10&start=1&sort=sim";
        try {
            String resStr = HttpUtils.get(url, headers);
            log.debug("resStr: [{}]", resStr);
//            return parseItem(resStr);
            return parseItem(resStr, keywordId, agencyId);
        } catch (IOException | ParseException e) {
            log.error("ERROR: ", e);
            throw new RuntimeException(e);
        }
    }

//    public List<News> parseItem(String resStr) throws ParseException, JsonProcessingException {
    public List<News> parseItem(String resStr, Long keywordId, Long agencyId) throws ParseException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject resObj = objectMapper.readValue(resStr, JSONObject.class);
        Object items = resObj.get("items");
        List<JSONObject> itemList = objectMapper.convertValue(items, new TypeReference<List<JSONObject>>() { });
        log.debug("itemList: [{}]", itemList);


        List<News> newsList = new ArrayList<>();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (JSONObject item : itemList) {
            News news = objectMapper.convertValue(item, News.class);

            news.setOrgLink((String) item.get("link"));
            news.setContent((String) item.get("description"));
            news.setPublishedAt((String) item.get("pubDate"));
            news.setKeywordId(keywordId);
            news.setAgencyId(agencyId);
            log.debug("news : [{}]", news);
            newsList.add(news);
        }

        return newsList;
    }
}


