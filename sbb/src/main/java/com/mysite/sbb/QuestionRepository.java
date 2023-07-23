package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    //<리포지토리의 대상이 되는 엔티티의 타입, 해당 엔티티의 PK 속성 타입>
    Question findBySubject(String subject);
    //findBy~~~는 기본적으로 뒤의 속성으로 데이터를 찾아주는 쿼리를 JPA가 자동으로 만들어줌
    Question findBySubjectAndContent(String subject, String content);
    
    List<Question> findBySubjectLike(String subject);  //특정 문자열이 포함되어 있는 데이터들 가져옴
}
