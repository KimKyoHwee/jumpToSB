package com.mysite.sbb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private static final Logger logger=LoggerFactory.getLogger(MainController.class);
    @GetMapping("/sbb")
    @ResponseBody  // 없으면 return되는 "index"라는 이름의 템플릿 파일을 찾는다.
    public String index(){
        logger.info("index method on");
        return "안녕하세요, sbb에 오신 것을 환영합니다.";
    }
    
    @GetMapping("/")  //기본화면 매핑
    public String root(){
        return "redirect:/question/list";  //redirect:<URL>  : URL로 완전 새로운 요청
                                           //forward:<URL>   : URL로 기존 요청값 유지한채로 요청
    }
}
