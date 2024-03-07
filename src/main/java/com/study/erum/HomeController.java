package com.study.erum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// Spring MVC 컨트롤러와 요청 매핑을 위한 기본 어노테이션들을 임포트

import lombok.extern.log4j.Log4j;
// Lombok 라이브러리를 사용하여 Log4j 로그 객체를 자동으로 생성

/**
 * Handles requests for the application home page.
 */
// 애플리케이션 홈 페이지에 대한 요청을 처리하는 클래스에 대한 설명
@Controller
// 이 클래스가 웹 요청을 처리하는 컨트롤러 역할을 함을 스프링 프레임워크에 알림
@Log4j
// Lombok 라이브러리의 Log4j 어노테이션을 사용하여 로그 기능을 클래스에 자동으로 추가

public class HomeController {
    // HomeController 클래스 시작

    @RequestMapping({"","/"}) 
    // 루트("/") 경로와 빈 문자열("") 경로의 요청을 이 메서드가 처리하도록 설정. 다중 경로 매핑을 위해 중괄호 사용
    public String home() {
        // 홈 페이지 요청을 처리하는 메서드
        log.info("Welcome home!");
        // 로그에 "Welcome home!" 메시지 기록. 애플리케이션 홈 페이지가 요청될 때마다 로그 출력
        return "index";
        // 홈 페이지를 보여주는 뷰의 이름을 반환. 여기서는 "index.jsp" 뷰로 이동하게 됨
    }
    // 클래스 종료
}
