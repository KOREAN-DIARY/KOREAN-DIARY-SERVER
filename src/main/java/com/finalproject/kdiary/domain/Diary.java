package com.finalproject.kdiary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(nullable = false)
    private int writingScore;

    @Column(nullable = false)
    private int speakingScore;


    @Builder
    public Diary(String content, Date date, int writingScore, int speakingScore) {
        this.content = content;
        this.date = date;
        this.writingScore = writingScore;
        this.speakingScore = speakingScore;
    }
}
