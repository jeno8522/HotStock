package com.ssafy.hotstock.domain.theme.domain;

import static jakarta.persistence.CascadeType.ALL;

import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Long id;

    @Column(name = "theme_name")
    private String name;

    @OneToMany(mappedBy = "theme", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();


}
