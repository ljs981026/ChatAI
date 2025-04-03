package com.example.chatai.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Operation(summary = "테스트 엔드포인트", description = "Swagger 적용 확인용")
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello";
    }
}
