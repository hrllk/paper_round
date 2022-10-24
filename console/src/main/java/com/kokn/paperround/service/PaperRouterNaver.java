package com.kokn.paperround.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("PaperRouterNaver")
@Slf4j
public class PaperRouterNaver implements PaperRouter{
    @Override
    public Object getNews(String keyword) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("X-Naver-Client-Id", "qXPada7nrmo0GeQ1aio9");
            httpHeaders.add("X-Naver-Client-Secret", "zT2QlFbooc");

            String url = "https://openapi.naver.com/v1/search/news.json?query=" + keyword + "&display=10&start=1&sort=sim";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), String.class);
            if (response != null && response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om.readValue(response.getBody(), Object.class);

            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
