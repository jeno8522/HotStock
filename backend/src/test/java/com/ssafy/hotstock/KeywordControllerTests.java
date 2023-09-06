//package com.ssafy.hotstock;
//import com.ssafy.hotstock.domain.keyword.domain.Keyword;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//// 에러나는 코드 -> 수정 예정
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class KeywordControllerTests{
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testCreateKeyword() {
//        // 더미 데이터 설정
//        Map<String, Object> stock1 = new HashMap<>();
//        stock1.put("name", "ABC Bank");
//        stock1.put("content", "A top-tier bank");
//        stock1.put("theme", "Banking");
//
//        Map<String, Object> theme1 = new HashMap<>();
//        theme1.put("name", "Banking");
//        theme1.put("keywordThemes", Arrays.asList("Investment", "Loans"));
//        theme1.put("stocks", Collections.singletonList(stock1));
//
//        Map<String, Object> keywordTheme1 = new HashMap<>();
//        keywordTheme1.put("keyword", "Finance");
//        keywordTheme1.put("theme", theme1);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("content", "Sample Content");
//        requestBody.put("createDate", "2023-09-05T07:10:05.257Z");
//        requestBody.put("keywordThemes", Collections.singletonList(keywordTheme1));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<Keyword> responseEntity = restTemplate
//                .postForEntity("/hotstock/keyword", requestEntity, Keyword.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isNotNull();
//        assertThat(responseEntity.getBody().getContent()).isEqualTo("Sample Content");
//        // 여기에 추가적인 assert 구문을 작성할 수 있습니다.
//    }
//}
//
//
