package com.ssafy.hotstock.domain.theme.repository;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByName(String name);
}
