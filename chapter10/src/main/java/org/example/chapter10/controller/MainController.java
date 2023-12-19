package org.example.chapter10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final Logger log = LoggerFactory.getLogger(MainController.class);

    // /test 엔드포인트를 요청하는 main.html 페이지 정의
    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    // CORS 작동을 확인하기 위해 다른 출처에서 호출할 엔드포인트 정의
    @PostMapping("/test")
    @CrossOrigin("http://localhost:8080")
    @ResponseBody
    public String test() {
        log.info("Test Method called");
        return "HELLO";
    }

}
