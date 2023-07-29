package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {  //전달되는 입력값(Question 엔티티 클래스)을 검증하기 위한 폼 클래스
    //공백으로 입력되어도 DB에 저장되는 상황을 막기 위하여 사용
    //implementation 'org.springframework.boot:spring-boot-starter-validation'
    @NotEmpty(message="제목은 필수 사항입니다.")
    @Size(max=200)
    private String subject;
    
    @NotEmpty(message = "내용은 필수 사항입니다.")
    private String content;
}
