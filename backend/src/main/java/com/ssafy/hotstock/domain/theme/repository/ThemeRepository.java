package com.ssafy.hotstock.domain.theme.repository;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@Hidden
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findByName(String name);
}
