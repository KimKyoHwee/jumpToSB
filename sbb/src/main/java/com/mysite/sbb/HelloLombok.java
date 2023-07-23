package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
//@Setter
@RequiredArgsConstructor  //final로 생성된 변수들이 생성자에 자동 포함(Setter 사용 불가)
public class HelloLombok {
//롬복으로 컴파일된 클래스에는 Getter과 Setter 메소드가 실제로 포함됨
    private final String hello;
    private final int lombok;
    public static void main(String[] args) {
        HelloLombok helloLombok=new HelloLombok("해윙", 5);
        //helloLombok.setHello("해윙");
        //helloLombok.setLombok(5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }

}
