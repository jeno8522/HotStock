package com.ssafy.hotstock.domain.news.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByMediaCompanyNum(int mediaCompanyNum);
}
