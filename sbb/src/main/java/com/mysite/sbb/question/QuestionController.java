package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor  //롬복 애너테이션, final 붙은 속성을 생성자에 자동 포함
@RequestMapping("/question")  //클래스 단위의 매핑
public class QuestionController {
    private final QuestionService questionService;  //자동 주입(RequiredArgsConstructor에 의하여)

    @GetMapping("/list")
    public String list(Model model){ //컨트롤러의 매개변수에 Model 객체 선언하면 자동으로 생성
        List<Question> questionList=this.questionService.getList()  ;
        model.addAttribute("questionList", questionList);  //Model객체에 리스트 저장.
        //Model객체는 자바 클래스와 템플릿을 연결. 저장해두면 템플릿에서 사용 가능 
        return "question_list";
    }

    @GetMapping(value="/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm ){
        Question question=this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult){
        //@Valid로 검증기능됨(검증 결과는 BindingResult객체에 저장)
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
}
