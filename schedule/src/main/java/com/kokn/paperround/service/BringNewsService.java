package com.kokn.paperround.service;

import com.kokn.paperround.entity.Agency;
import com.kokn.paperround.entity.Keyword;
import com.kokn.paperround.entity.News;
import com.kokn.paperround.repository.AgencyRepository;
import com.kokn.paperround.repository.KeywordRepository;
import com.kokn.paperround.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BringNewsService {

    private final KeywordRepository keywordRepository;
    private final NewsRepository newsRepository;
    private final AgencyRepository agencyRepository;

    public void bringNews(){

        /***
         * 1. 키워드리스트 가져오기,
         * 2. 제휴목록가져오기
         * 3. 2중포문을통해 뉴스요청
         */
        List<Keyword> keywordList = getKeywordList();
        List<Agency> agencyList = getAgencyList();

        for (Agency agency : agencyList) {
            for (Keyword keyword : keywordList) {

                List<News> newsList = getNewsEachAgency(keyword, agency);

                for (News news : newsList) {
                    String title = news.getTitle();
                    if (newsRepository.findByTitle(title).isPresent()){
                        log.info("[{}] already exist:, title");
                        continue;
                    }

                    newsRepository.save(news);
                }
            }
        }
    }

    private List<Keyword> getKeywordList(){
        return keywordRepository.findAll();
    }

    private List<Agency> getAgencyList() {
        return agencyRepository.findAll();
    }


    public List<News> getNewsEachAgency(Keyword keyword, Agency agency) {

        // 여기서 reflection
        String packageName = "com.kokn.paperround.service";
        String agencyName = agency.getAgencyName();
        String clazzName = "BringNewsService_" + agencyName;
        String classPath = String.format("%s.%s", packageName, clazzName);

        try {
            Class<?> bringNewsClazz = Class.forName(classPath);
            Constructor<?> constructor = bringNewsClazz.getConstructor();
//            Method method = bringNewsClazz.getDeclaredMethod("getNews", String.class);
             Method method = bringNewsClazz.getDeclaredMethod("getNews", new Class[]{String.class, Long.class, Long.class});

            return (List<News>) method.invoke(constructor.newInstance(), keyword.getKeyword(), keyword.getKeywordId(), agency.getAgencyId());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error("ERROR: ", e);
            throw new RuntimeException("ERROR");
        }
    }
}
