package com.khit.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.entity.User;
import com.khit.board.service.UserSerivce;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller  //RestController -> @Controller + @ResponseBody + @RequestBody
public class UserController {

	private final UserSerivce userService;
	
	//회원 가입
	//포스트맨에 json 형태의 자료를 입력받아서 db에 저장함
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		userService.save(user);
		return "회원 가입 성공";
	}
	
	//회원 목록 보기
	@GetMapping("/user/list")
	public @ResponseBody List<User> getList() {
		List<User> userList =  userService.findAll();
		return userList;
	}
	
	//회원 상세 보기
	@PostMapping("/user/{id}")
	//전송방식 - PUT
	public @ResponseBody User getUser(@PathVariable Integer id) {
		//검색된 회원이 없을 경우 예외 반환
		User user = userService.findById(id);
		return user;
	}
	
	//회원 수정
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		userService.update(user);
		return "회원 수정 성공!!";
	}
	
	//회원 삭제
	//전송방식 - DELETE
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		return "회원 정보 삭제";
	}
	
}
