package com.mysite.sbb.answer;
//엔티티클래스 (JPA활용한 DB의 테이블 모델) - DB에 직접 데이터 저장이나 조회 불가 (속성 관리만)

import com.mysite.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne  //한개의 질문에 여러개의 답변이 달릴 수 있다. 즉 N:1관계.(Answer엔티티의 question과 Question엔티티)
    private Question question;
}
