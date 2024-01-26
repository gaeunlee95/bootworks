package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.board.entity.Users;

@RestController  //데이터 전달 역할의 어노테이션임
public class SampleController {

	@GetMapping("/sample")
	public String test() {
		return "안녕하세요";  //Controller: main -> main.html //RestController : main -> main(문자)
	}

	//GET - SELECT
	//객체를 반환하면 - json 형태로 전달됨
	@GetMapping("/khit")
	public Users httpGet() {
		//user 1명을 생성한 후 데이터 검색(보기)
		//Users user = new Users();
		//user.setId(1);
		Users user = Users.builder()
				.id(1)
				.username("today")
				.password("1234")
				.email("cloud@sky.com")
				.build();
		
		return user;
	}
	
	//POST - INSERT
	//전달받은 데이터가 json 형태({key:value})일때 @RequestBody 사용
	@PostMapping("/khit")
	public String httpPost(@RequestBody Users users) {
		return "POST 요청 처리" + users.toString();
	}
	
	//PUT - UPDATE
	@PutMapping("/khit")
	public String httpPut(@RequestBody Users users) {
		return "PUT 요청 처리" + users.toString();
	}
	
	//DELETE - DELETE
	// @PathVariable은 경로 변수를 전달 받음
	@DeleteMapping("/khit/{id}")
	public String httpDelete(@PathVariable Integer id) {
		return "Delete 요청 처리 " + id;
	}
}
