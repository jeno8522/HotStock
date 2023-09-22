package com.ssafy.hotstock.domain.keyword.repository;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByContent(String content);

    @Query(value = "SELECT k FROM Keyword k WHERE k.count > 0 ORDER BY k.count DESC")
    List<Keyword> findKeywordsByCount(Pageable pageable);
}
