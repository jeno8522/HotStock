package com.ssafy.hotstock;

import com.ssafy.hotstock.domain.keywordsummary.KeywordSummary;
import com.ssafy.hotstock.domain.keywordtheme.KeywordTheme;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.stock.Stock;
import com.ssafy.hotstock.domain.theme.Theme;
import com.ssafy.hotstock.domain.keyword.Keyword;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class HotstockApplication implements CommandLineRunner {

	private final EntityManager em;

	public HotstockApplication(EntityManager em) {
		this.em = em;
	}

	public static void main(String[] args) {
		SpringApplication.run(HotstockApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		// POST keyword 더미데이터
		// request body 더미데이터를 main에서 강제로 집어넣음 -> swagger ui용 request body 더미데이터는 keywordController에 있음.


		KeywordSummary keywordSummary = new KeywordSummary();
		keywordSummary.setCount(5L);
		keywordSummary.setCreateDate(LocalDateTime.parse("2023-09-05T07:28:55.382"));
		em.persist(keywordSummary);


		News news = new News();
		news.setTitle("Finance News Title");
//		news.setSummary("This is a summary");
		news.setLink("http://example.com");
		em.persist(news);




		Theme theme = new Theme();
		theme.setName("Banking");
		em.persist(theme);


		Stock stock1 = new Stock();
		stock1.setName("ABC Bank");
		stock1.setContent("Top-tier bank");
		stock1.setTheme(theme);
		em.persist(stock1);


		KeywordTheme keywordTheme = new KeywordTheme();
		keywordTheme.setTheme(theme);
		em.persist(keywordTheme);


		Keyword keyword = new Keyword();
		keyword.setContent("Finance Related");
		keyword.setCreateDate(LocalDateTime.parse("2023-09-05T07:28:55.382"));
		keyword.setKeywordThemes(Arrays.asList(keywordTheme));
		keyword.setNews(news);
		keyword.setKeywordSummary(keywordSummary);
		em.persist(keyword);
	}
}
