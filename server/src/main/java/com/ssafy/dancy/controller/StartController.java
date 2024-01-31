package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.TesterEntity;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.TestSaveRequest;
import com.ssafy.dancy.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/start")
public class StartController {

    private final TestService testService;

    @Value("${JASYPT_KEY")
    String jasyptKey;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Dancy!"+
                jasyptKey;
    }

    @GetMapping("/test/querydsl/{name}")
    public List<TesterEntity> queryDslTest(@PathVariable String name){
        return testService.getTestUsingName(name);
    }

    @GetMapping("/test/jpa/{address}")
    public List<TesterEntity> jpaTest(@PathVariable String address){
        return testService.getTestUsingAddress(address);
    }

    @PostMapping("/test/save")
    public void testSave(@RequestBody TestSaveRequest request){
        testService.saveTestEntity(request);
    }

    @PostMapping("/test/valid")
    public void validationTest(@Valid @RequestBody TestSaveRequest request){

    }

    @GetMapping("/test/userinfo")
    public String validateUser(@AuthenticationPrincipal User user){
        return user.getNickname();
    }
}
