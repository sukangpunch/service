package com.delivery.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan : @SpringBootApplication 이 선언된 클래스의 "패키지"와 그 하위 패키지를 기준으로 빈 등록
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);
    }
}