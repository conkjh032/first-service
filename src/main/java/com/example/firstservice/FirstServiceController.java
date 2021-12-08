package com.example.firstservice;

import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    Environment env;

    // Autowired를 변수 위에 바로 선언하지 말고 아래와 같이
    // Autowired는 생성자를 통해서 선언하도록 권장
    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
        log.info(header);
        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(){
        return "Hi, there. Thie is a message from First Service";
    }

    @GetMapping("/check/port")
    public String check(HttpServletRequest request){
        log.info("Server port = {}", request.getServerPort());
        return String.format("Hi, there. Thie is a message from First Service on Port %s", env.getProperty("local.server.port"));
    }
}
