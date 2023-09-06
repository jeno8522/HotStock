package com.ssafy.hotstock.domain.keyword.domain;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

}
