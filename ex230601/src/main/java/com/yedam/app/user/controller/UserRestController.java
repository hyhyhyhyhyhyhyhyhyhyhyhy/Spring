package com.yedam.app.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.user.service.UserVO;

// @Controller
@RestController // 해당 컨트롤러의 모든 메서드는 데이터를 반환한다. (페이지 요청 메서드 사용 불가)
public class UserRestController {
	
	@PostMapping("insertUser")
	// @ResponseBody // 데이터를 반환하는 메서드
	public UserVO insertUser(UserVO userVO) {
		System.out.println("name : " + userVO.getName());
		System.out.println("age : " + userVO.getAge());
		return userVO;
	}
	
	@GetMapping("getHome")
	public String getHome() {
		return "home";
	}
	
}