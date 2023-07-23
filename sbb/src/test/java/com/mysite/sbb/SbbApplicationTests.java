package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
//		Question question1=new Question();
//		question1.setSubject("sbb가 무엇인가요?");
//		question1.setContent("sbb에 대하여 알고 싶습니다.");
//		question1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(question1);
//
//		Question q2=new Question();
//		q2.setSubject("스프링 부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
		List<Question> all=this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q= all.get(0);  //id로 찾는 것이 아닌 첫번째로 저장한 것 가져옴
		assertEquals("sbb가 무엇인가요?", q.getSubject());

		Optional<Question> oq=this.questionRepository.findById(1);  //id로 찾아옴(Optional로 반환함)
		if(oq.isPresent()){  //null이 아니면,
			Question q2=oq.get();
			assertEquals("sbb가 무엇인가요?", q2.getSubject());
		}

		Question q3=this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q3.getId());
		
		List<Question> qList=this.questionRepository.findBySubjectLike("sbb%");
		//Like 검색에서 
		//sbb% : sbb로 시작하는 문자열.  %sbb : sbb로 끝나는 문자열.  %sbb% : sbb를 포함하는 문자열
		Question q4=qList.get(0);
		assertEquals("sbb가 무엇인가요?", q4.getSubject());
	}

}
