package com.ssafy.hotstock.domain.news.controller;


import com.ssafy.hotstock.domain.news.dto.NaverApiItemsResponseDto;
import com.ssafy.hotstock.domain.news.service.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/naver")
    public List<NaverApiItemsResponseDto> naver(@RequestParam(value = "search") String search,
        @RequestParam(value = "display") int display) {

        List<NaverApiItemsResponseDto> naverApiItemsResponseDtoList = newsService.naverApi(search,
            display);

        return naverApiItemsResponseDtoList;
    }
}
