package com.ssafy.hotstock.domain.keywordsummary.domain;

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
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordCheckPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="check_point_id")
    private Long id;

    @Column(name="check_time")
    private String checkTime;

    @OneToMany(mappedBy = "keywordCheckPoint")
    private List<KeywordCountLog> keywordCountLog = new ArrayList<>();


}
