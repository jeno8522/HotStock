package com.ssafy.hotstock.domain.news.repository;

import com.ssafy.hotstock.domain.news.domain.News;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@Repository
@Repository
@Hidden
public interface NewsRepository extends JpaRepository<News, Long> {
}
