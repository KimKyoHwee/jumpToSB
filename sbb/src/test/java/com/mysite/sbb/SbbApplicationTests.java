package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@Transactional  //메서드가 종료될 때까지 DB 세션이 유지(DB와 연결이 안끊어짐)
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

		//질문 수정하는 테스트 코드
		Optional<Question> oq2=this.questionRepository.findById(1);
		assertTrue(oq2.isPresent());
		Question q5=oq2.get();
		q5.setSubject("수정된 제목");
		this.questionRepository.save(q5);

		//데이터 삭제 테스트
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq3=this.questionRepository.findById(1);
		assertTrue(oq3.isPresent());
		Question q6=oq3.get();
		this.questionRepository.delete(q6);
		assertEquals(1, this.questionRepository.count());

		//답변 데이터 생성 후 저장
		Optional<Question> oq4=this.questionRepository.findById(2);
		assertTrue(oq4.isPresent());
		Question q7=oq4.get();
		Answer answer=new Answer();
		answer.setContent("네 자동으로 생성됩니다.");
		answer.setQuestion(q7); //어떤 질문의 답변인지 알기 위하여 Question 객체 필요
		answer.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(answer);

		//답변 조회
		Optional<Answer> oa=this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a=oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

}
