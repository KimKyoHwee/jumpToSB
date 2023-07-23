package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter  //엔티티 클래스에서는 @Builder 어노테이션 사용하는것 추천.
@Entity //JPA를 사용한 엔티티 클래스(DB테이블의 모델이라는 뜻)
public class Question {
    @Id   //primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //1씩 자동 증가, IDENTITY 안쓰면 모든 column이 동일한 시퀀스로 번호 생성
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT") //Column속성 정의. "TEXT"는 글자 수 제한 불가할때 사용
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    //Answer클래스의 question는 참조 엔티티의 속성 이름
    //cascade : 질문을 삭제하면 그에 달린 답변들도 모두 삭제
    private List<Answer> answerList;  //각 질문에 따른 답변들의 List
}
