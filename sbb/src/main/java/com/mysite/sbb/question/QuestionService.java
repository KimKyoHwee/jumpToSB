package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

//서비스에서 return하는 것은 보통 DTO 객체이다.  리포지토리와 엔티티객체(모델)로 소통, 컨트롤러와 DTO객체로 소통
//DTO는 비즈니스 모델에서 사용하는 객체. 즉 서비스는 엔티티-DTO 객체들을 양방향에 전달하는 역할
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> question=this.questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        }
        else throw new DataNotFoundException("question is not found");
    }

    public void create(String subject, String content){
        Question q=new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
