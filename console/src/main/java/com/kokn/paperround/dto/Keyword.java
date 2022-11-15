package com.kokn.paperround.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class Keyword {

    private String keyword;
    private int useCnt;

    public static List<Keyword> fromEntity(List<com.kokn.paperround.entity.Keyword> keywords) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Keyword> keywordList = om.convertValue(keywords, new TypeReference<List<Keyword>>() { });
        return keywordList;
    }
}
