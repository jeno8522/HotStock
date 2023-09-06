package com.ssafy.hotstock.domain.theme.domain;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
