package com.khit.board.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.khit.board.entity.Member;

//사용자 정의 필드를 추가하여, 인증 과정에서 사용자의 추가 정보를 활용
//예를 들어, 인증된 사용자의 역할이나 기타 특정 정보에 접근

//User 클래스 상속: 스프링 시큐리티의 기본 사용자 정보와 추가적인 사용자 정보를 함께 관리
public class SecurityUser extends User {
	
	private Member member;
	//3가지 파라미터 - username, password, role
	public SecurityUser(Member member) {
		//암호환 안된 상태: {noop} 사용
		super(member.getMemberId(),member.getPassword(), 
				//AuthorityUtils.createAuthorityList 메소드: Member 객체의 역할을 권한 목록으로 변환
				AuthorityUtils.createAuthorityList(member.getRole().toString()) );
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
}
