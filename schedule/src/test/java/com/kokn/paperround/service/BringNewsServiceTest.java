package com.kokn.paperround.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
public class BringNewsServiceTest {


    @Autowired
    private BringNewsService bringNewsService;
    @Test
    void name() {
        log.debug("hi");
        log.debug("hi");
        log.debug("hi");
        log.debug("hi");
        log.debug("hi");
        log.debug("hi");


        // Given
        String keyword = "한동훈";
        BringNewsService_Naver naver = new BringNewsService_Naver();

        // When
//        List<News> newsList = naver.getNews(keyword);


        // Then
    }
    @Test
    void test_pare_naver_json() throws ParseException {


        // Given
        String responseData = "{\n" +
                "    \"lastBuildDate\":\"Thu, 01 Dec 2022 14:45:43 +0900\",\n" +
                "    \"total\":117549,\n" +
                "    \"start\":1,\n" +
                "    \"display\":10,\n" +
                "    \"items\":[\n" +
                "        {\n" +
                "            \"title\":\"&apos;<b>한동훈<\\/b> 독직폭행&apos; 무죄 확정에도‥검사끼리 서로 &quot;네 잘못&quot;\",\n" +
                "            \"originallink\":\"https:\\/\\/imnews.imbc.com\\/replay\\/2022\\/nwdesk\\/article\\/6432142_35744.html\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/214\\/0001238627?sid=102\",\n" +
                "            \"description\":\"이른바 &apos;채널 A&apos;사건 수사 과정에서 휴대 전화를 압수하려는 수사 검사와, 뺏기지 않으려는 <b>한동훈<\\/b> 법무 장관... ◀ 리포트 ▶ 지난 2020년 7월, &apos;채널A 사건&apos; 주임검사인 정진웅 부장검사는, 연루 의혹을 받고 있던 <b>한동훈<\\/b>... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 20:36:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"&apos;<b>한동훈<\\/b> 독직폭행&apos; 혐의 정진웅, 오늘 대법 선고…2심 무죄\",\n" +
                "            \"originallink\":\"http:\\/\\/www.newsis.com\\/view\\/?id=NISX20221129_0002104832&cID=10201&pID=10200\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/003\\/0011565802?sid=102\",\n" +
                "            \"description\":\"기사내용 요약 1심은 폭행 미필적 고의 인정…유죄 2심 &quot;넘어진 것&quot; 취지로 인정…무죄 [서울=뉴시스] 류인선 기자 = &apos;채널A 사건&apos; 관련 압수수색 과정에서 <b>한동훈<\\/b> 법무부 장관을 폭행한 혐의로 재판에 넘겨진 정진웅... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 05:00:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"대법, <b>한동훈<\\/b> &apos;독직폭행&apos; 혐의 정진웅 무죄 확정\",\n" +
                "            \"originallink\":\"https:\\/\\/view.asiae.co.kr\\/article\\/2022113014251231856\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/277\\/0005185238?sid=102\",\n" +
                "            \"description\":\"압수수색 과정에서 <b>한동훈<\\/b> 법무부 장관을 폭행해 상해를 입힌 혐의로 재판에 넘겨진 정진웅 법무연수원 연구위원(54·사법연수원 29기)의 무죄가 확정됐다. 대법원 1부(주심 박정화 대법관)는 이날 오후 특정범죄가중법상... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 14:25:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"독직폭행 무죄에 수사팀장 &quot;사과하라&quot;…<b>한동훈<\\/b> &quot;성찰하라&quot;(종합2보)\",\n" +
                "            \"originallink\":\"https:\\/\\/www.yna.co.kr\\/view\\/AKR20221130124352004?input=1195m\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/001\\/0013614176?sid=102\",\n" +
                "            \"description\":\"당시 &apos;채널A 사건&apos; 수사팀장 &quot;부당 기소 사과해야&quot; <b>한동훈<\\/b> &quot;직무 집행 정당했다는 건 아냐&quot;…정진웅 기소팀도 &quot;본질 호도&quot; 이대희 박재현 기자 = 대법원이 30일 <b>한동훈<\\/b> 장관을 독직 폭행한 혐의로 기소된 정진웅 법무연수원... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 17:51:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"‘<b>한동훈<\\/b> 독직폭행 혐의’ 정진웅 검사 무죄 확정\",\n" +
                "            \"originallink\":\"http:\\/\\/www.segye.com\\/content\\/html\\/2022\\/11\\/30\\/20221130514667.html?OutUrl=naver\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/022\\/0003759632?sid=102\",\n" +
                "            \"description\":\"<b>한동훈<\\/b> 법무부 장관. 과천=연합뉴스 <b>한동훈<\\/b> 법무부 장관을 압수수색 하는 과정에서 폭행한 혐의로 기소된 정진웅 법무연수원 연구위원(차장검사)의 무죄가 확정됐다. 30일 대법원 1부(주심 박정화 대법관)는 검찰의 상고를... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 16:15:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"<b>한동훈<\\/b>과 육탄전? 채널A 수사검사 &apos;무죄&apos; 확정 \",\n" +
                "            \"originallink\":\"http:\\/\\/www.mediatoday.co.kr\\/news\\/articleView.html?idxno=307213\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/006\\/0000115775?sid=102\",\n" +
                "            \"description\":\"2020년 &apos;채널A 검언유착 의혹&apos; 사건을 수사했던 정진웅 차장검사(현 법무연수원 연구위원)의 <b>한동훈<\\/b>... 피해자(<b>한동훈<\\/b> 검사장)의 범행 공모 여부를 조사하기 위한 휴대전화 유심칩 압수수색영장 집행 과정에서 피해자에게... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 16:36:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"‘<b>한동훈<\\/b> 독직폭행’ 정진웅 무죄 확정\",\n" +
                "            \"originallink\":\"https:\\/\\/www.khan.co.kr\\/national\\/court-law\\/article\\/202211302130045\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/032\\/0003189902?sid=102\",\n" +
                "            \"description\":\"‘검·언 유착’ 의혹을 수사하다 <b>한동훈<\\/b> 법무부 장관을 폭행한 혐의로 재판에 넘겨진 정진웅 법무연수원... 통해 “<b>한동훈<\\/b> 전 검사장이 수사의 정당성을 훼손하기 위해 적법한 공무집행행위를 악의적인 ‘권력의 폭력’... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 21:32:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"&apos;<b>한동훈<\\/b> 독직폭행&apos; 혐의 정진웅, 오늘 대법원 선고\",\n" +
                "            \"originallink\":\"http:\\/\\/mbn.mk.co.kr\\/pages\\/news\\/newsView.php?category=mbn00009&news_seq_no=4882839\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/057\\/0001706080?sid=102\",\n" +
                "            \"description\":\"<b>한동훈<\\/b> 법무부 장관을 폭행한 혐의로 재판에 넘겨진 정진웅 법무연수원 연구위원에 대해 오늘(30일) 대법원이 선고를 내립니다. 정 연구위원은 지난 2020년, 이른바 &apos;채널A 사건&apos; 압수수색 과정에서 당시 법무연수원에서... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 07:01:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"&apos;<b>한동훈<\\/b> 독직폭행&apos; 정진웅 무죄 확정…대법 &quot;고의 인정 안 돼&quot;\",\n" +
                "            \"originallink\":\"https:\\/\\/www.busan.com\\/view\\/busan\\/view.php?code=2022113015593735745\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/082\\/0001186361?sid=102\",\n" +
                "            \"description\":\"지난 2020년 이른바 &apos;검언유착&apos; 의혹과 관련해 압수수색 과정에서 <b>한동훈<\\/b> 검사장(현 법무부 장관)을 폭행한 혐의로 기소된 정진웅 법무연수원 연구위원(차장검사)의 무죄가 대법원에서 확정됐다. 30일 대법원 1부(주심... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 16:33:00 +0900\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\":\"&apos;<b>한동훈<\\/b> 독직폭행&apos; 정진웅 오늘 대법 선고…2심 무죄\",\n" +
                "            \"originallink\":\"https:\\/\\/www.news1.kr\\/articles\\/4879601\",\n" +
                "            \"link\":\"https:\\/\\/n.news.naver.com\\/mnews\\/article\\/421\\/0006490390?sid=102\",\n" +
                "            \"description\":\"압수수색 과정에서 <b>한동훈<\\/b> 법무부 장관을 폭행한 혐의로 재판에 넘겨진 정진웅 법무연수원 연구위원에 대한 대법원 판단이 30일 나온다. 대법원 1부(주심 박정화 대법관)는 이날 오후 특정범죄가중처벌법상 독직폭행... \",\n" +
                "            \"pubDate\":\"Wed, 30 Nov 2022 05:00:00 +0900\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        BringNewsService_Naver naver = new BringNewsService_Naver();

        // When
//        try {
//            naver.parseItem(responseData);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        // Then
    }


    @Test
    void test_bring_news_by_keyword() {

        // given
        bringNewsService.bringNews();
        // when

        // then
    }
}
