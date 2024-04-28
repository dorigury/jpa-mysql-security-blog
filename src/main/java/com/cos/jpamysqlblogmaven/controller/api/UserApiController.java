package com.cos.jpamysqlblogmaven.controller.api;

import com.cos.jpamysqlblogmaven.dto.ResponseDto;
import com.cos.jpamysqlblogmaven.model.User;
import com.cos.jpamysqlblogmaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;
    // 회원가입
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("User Service: save 호출됨");
        userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 회원수정
    // 로그인
    // 회원정보
    // 권한 관리




}
