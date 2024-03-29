package com.khit.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	private final PasswordEncoder pwEncoder;
	
	public Member login(Member member) {
		// db에서 아이디 조회
		Optional<Member> findMember = 
				memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}

	public void save(Member member) {
		//1. 비밀번호 암호화
		//2. 권한 설정
		String encPW = pwEncoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.Member);
		
		memberRepository.save(member);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public void delete(Integer id) {
		memberRepository.deleteById(id);
	}

	public Member findById(Integer id) {
		Optional<Member> findMember = memberRepository.findById(id);
		if(findMember.isPresent()) {  //회원 정보가 있으면
			return findMember.get();  //정보를 가져와서 반환
		}else {
			throw new BootBoardException("페이지를 찾을 수 없습니다.");
		}
	}

	public void update(Member member) {
		String encPW = pwEncoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.Member);
		memberRepository.save(member);
	}

}
