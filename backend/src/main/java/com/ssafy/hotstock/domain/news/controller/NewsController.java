package com.ssafy.hotstock.domain.news.controller;


import com.ssafy.hotstock.domain.news.dto.NaverApiItemsResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.service.NewsService;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/list")
    public ResponseEntity<?> addNews(@RequestParam int mediaCompanyNum,
        @RequestParam int articleNum) {
        long start = System.currentTimeMillis();

        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String format = currentTime.format(formatter);

        List<NewsResponseDto> newsResponseDtoList = newsService.crawlingNewsList(mediaCompanyNum,
            articleNum, format);

        long end = System.currentTimeMillis();

        long tmp = end - start;

        System.out.println("tmp = " + tmp);

        return new ResponseEntity<>(newsResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/naver")
    public List<NaverApiItemsResponseDto> naver(@RequestParam(value = "search") String search,
        @RequestParam(value = "display") int display) {

        List<NaverApiItemsResponseDto> naverApiItemsResponseDtoList = newsService.naverApi(search,
            display);

        return naverApiItemsResponseDtoList;
    }
}
