package com.ssafy.hotstock.domain.theme.domain;

import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

    @Id @GeneratedValue
    @Column(name = "theme_id")
    private Long id;

    @Column(name = "theme_name")
    private String name;

    @OneToMany(mappedBy = "theme", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();


}
