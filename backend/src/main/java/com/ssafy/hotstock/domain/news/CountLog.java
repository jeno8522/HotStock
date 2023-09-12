package com.ssafy.hotstock.domain.news.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.yaml.snakeyaml.events.Event.ID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="count_log_id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "sub_count")
    private int subCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="check_point_id")
    private CheckPoint checkPoint;
}
