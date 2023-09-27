package com.ssafy.hotstock.domain.news.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.hotstock.domain.news.domain.Media;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.NaverApiItemsResponseDto;
import com.ssafy.hotstock.domain.news.dto.NaverApiResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.repository.NewsRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final MediaService mediaService;


    /**
     * 최신 뉴스 기사 번호 찾기 mediaCompanyNum : 언론사 번호
     */
    @Override
    public int findArticleNum(int mediaCompanyNum) {
        String articleNum = null;
        try {
            String link =
                "https://media.naver.com/press/" + String.format("%03d", mediaCompanyNum)
                    + "/ranking?type=popular";

            Connection conn = Jsoup.connect(link)
                .userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");

            Document doc = conn.get();

            String href = doc.select("li.as_thumb a").attr("href");

            // URL에서 숫자 부분을 추출하는 정규 표현식 패턴
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(href);

            // 숫자가 매칭된 경우 추출
            while (matcher.find()) {
                articleNum = matcher.group();
            }

        } catch (IOException e) {
            log.error("처음 최신 기사 번호 가져오기 오류: " + e.getCause());

        }

        if (articleNum == null) {
            return 1;
        } else {
            return Integer.parseInt(articleNum);
        }
    }


    /**
     * 뉴스 1개 크롤링 해오기 mediaCompanyNum : 언론사 번호 articleNum : 기사번호
     */

    @Override
    public News crawlingNews(int mediaCompanyNum, int articleNum) throws IOException {

        // Chrome 웹 드라이버 초기화
        WebDriver driver = new ChromeDriver();

        News news = new News();
        try {
            String link =
                "https://n.news.naver.com/article/" + String.format("%03d", mediaCompanyNum)
                    + "/" + String.format("%010d", articleNum);

            driver.get(link);

            String summaryContent="";

            while (true) {
                try {
                    // 요약 봇 버튼을 찾아 클릭
                    WebElement summaryButton = driver.findElement(
                        By.cssSelector("a.media_end_head_autosummary_button"));
                    summaryButton.click();

                    try {
                        // 요약된 내용을 가져오기 (선택자를 요약 봇 레이어의 클래스 이름으로 지정)
                        // 요약 내용이 로딩될 때까지 대기
                        // WebDriverWait를 사용하여 요약 봇 레이어가 나타날 때까지 대기
                        Duration timeout = Duration.ofSeconds(3);
                        WebDriverWait wait = new WebDriverWait(driver, timeout);
                        WebElement summaryContentElement = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                                "div.media_end_head_autosummary_layer_body ._contents_body._SUMMARY_CONTENT_BODY")));
                        summaryContent = summaryContentElement.getText();

                        break;
                    } catch (TimeoutException e) {
                        // TimeoutException 발생 시 알림 또는 로깅
                        log.error("TimeoutException 발생: 버튼 또는 요소가 나타나지 않음.");
                    }

                } catch (NoSuchElementException e) {
                    log.error("요약 봇 버튼을 찾을 수 없습니다.");
                    break;
                }
            }
            driver.close();

            Connection conn = Jsoup.connect(link)
                .userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");

            Document doc = conn.get();

            // 제목 찾아오기
            String title = doc.select("h2#title_area").text();

            // 시사/ 연예
            if (title.isEmpty()) {
                title = doc.select("h2.end_tit").text();
            }

            // 스포츠
            if (title.isEmpty()) {
                title = doc.select("h4.title").text();
            }

            // 본문 찾아오기
            String content = doc.select("article#dic_area").text();

            // 시사/ 연예
            if (content.isEmpty()) {
                Elements elements = doc.select("div#articeBody");
                if (!elements.isEmpty()) {
                    content = elements.text();
                } else {
                    System.out.println("해당 게시글의 본문을 찾을 수 없습니다.");
                }
            }

            // 스포츠
            if (content.isEmpty()) {
                Elements elements = doc.select("div#newsEndContents");
                if (!elements.isEmpty()) {
                    content = elements.text();
                } else {
                    System.out.println("해당 게시글의 본문을 찾을 수 없습니다.");
                }
            }

            // 날짜 찾아오기
            String date = doc.select("span.media_end_head_info_datestamp_time")
                .attr("data-date-time");

            // 시사/ 연예
            if (date.isEmpty()) {
                Elements elements = doc.select("span.author em");

                // 첫 번째 태그의 내용 가져오기
                if (!elements.isEmpty()) {
                    String dataTime = elements.first().text();
                    date = formatDateTime(dataTime);
                } else {
                    System.out.println("해당 날짜를 찾을 수 없습니다.");
                }
            }

            // 스포츠
            if (date.isEmpty()) {
                Elements elements = doc.select("div.news_headline span");

                // 첫 번째 태그의 내용 가져오기
                if (!elements.isEmpty()) {
                    String dataTime = elements.get(1).text();
                    dataTime = dataTime.replace("기사입력", "").trim();
                    date = formatDateTime(dataTime);
                } else {
                    System.out.println("해당 날짜를 찾을 수 없습니다.");
                }
            }

            news.setTitle(title);
            news.setContent(content);
            news.setLink(link);
            news.setDate(date);
            news.setMediaCompanyNum(mediaCompanyNum);
            news.setArticleNum(articleNum);
            news.setSummaryContent(summaryContent);


        } catch (IOException e) {
            throw e;
        }

        return news;

    }

    /**
     * 뉴스기사들 리스트로 가져오기
     */
    @Override
    public List<NewsResponseDto> crawlingNewsList(int mediaCompanyNum, int articleNum,
        String currentTime) {

        /**
         * 뉴스 가져온 후 저장
         * */
        List<News> newsList = new ArrayList<>();

        while (true) {
            try {
                News news = crawlingNews(mediaCompanyNum, articleNum);
                newsList.add(news);
                articleNum++;

                /**
                 * 현재 시간과 기사가 적혀진 시간을 비교하여 현재 시간까지의 최신 기사만을 가져온다.
                 */
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime articleTime = LocalDateTime.parse(news.getDate(), formatter);
                LocalDateTime currTime = LocalDateTime.parse(currentTime, formatter);
                if (articleTime.isAfter(currTime)) {
                    break;
                }
            } catch (IOException e) {
                log.error("Jsoup 연결 오류: " + e.getCause());
                // 정규 표현식을 사용하여 Status 값 추출
                Pattern pattern = Pattern.compile("Status=(\\d+)");
                Matcher matcher = pattern.matcher(e.getMessage());

                if (matcher.find()) {
                    String status = matcher.group(1); // 첫 번째 그룹을 가져옴
                    if (status.equals("404")) {
                        articleNum++;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Status 값을 찾을 수 없습니다.");
                }
            }
        }

        createNewsList(newsList);

        List<NewsResponseDto> newsResponseDtoList = new ArrayList<>();
        for (News news : newsList) {
            newsResponseDtoList.add(NewsResponseDto.from(news));
        }

        /**
         * 현재까지 가져온 기사의 번호 저장
         * */
        Media media = mediaService.getMediaByMediaCompanyNum(mediaCompanyNum);

        media.setCurrArticleNum(articleNum);

        mediaService.saveMedia(media);

        System.out.println("articleNum = " + articleNum);

        return newsResponseDtoList;

    }

    /**
     * 기사들의 시간 형식을 통일
     */
    @Override
    public String formatDateTime(String dataTime) {
        try {
            // 주어진 날짜와 시간 형식을 해석
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy.MM.dd. a hh:mm",
                Locale.KOREA);
            Date date = inputFormat.parse(dataTime);

            // 원하는 형식으로 출력 형식 지정
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public News insertNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> createNewsList(List<News> newsList) {
        return newsRepository.saveAll(newsList);
    }

    @Override
    public News findNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);  // JPA 에서는 업데이트도 save 사용
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<NaverApiItemsResponseDto> naverApi(String search, int display) {

        String encode = Base64.getEncoder().encodeToString(search.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/")
            .path("v1/search/news.json")
            .queryParam("query", search)
            .queryParam("display", display)
            .queryParam("start", 1)
            .queryParam("sort", "date")
            .encode()
            .build()
            .toUri();

        log.info("uri : {}", uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
            .get(uri)
            .header("X-Naver-Client-Id", "RVfg2ZH_Dwp1ldyiWDCk")
            .header("X-Naver-Client-Secret", "RIMVMjwQUn")
            .build();

        ResponseEntity<String> response = restTemplate.exchange(req, String.class);

        System.out.println("response.getBody() = " + response.getBody());

        List<NaverApiItemsResponseDto> naverApiResponseDtoList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            NaverApiResponseDto naverApiResponseDto = objectMapper.readValue(response.getBody(),
                NaverApiResponseDto.class);

            for (NaverApiItemsResponseDto naverApiItemsResponseDto : naverApiResponseDto.getItems()) {
                naverApiResponseDtoList.add(naverApiItemsResponseDto);
            }
        } catch (Exception e) {
            log.error("매핑에 실패했습니다.");
        }

        return naverApiResponseDtoList;
    }


}
