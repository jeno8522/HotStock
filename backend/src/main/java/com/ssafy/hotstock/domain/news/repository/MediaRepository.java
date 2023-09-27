package com.ssafy.hotstock.domain.news.repository;

import com.ssafy.hotstock.domain.news.domain.Media;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByMediaCompanyNum(int mediaCompanyNum);
}
