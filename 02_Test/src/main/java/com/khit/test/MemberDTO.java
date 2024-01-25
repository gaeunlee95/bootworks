package com.khit.test;

import com.khit.test.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
	private Long id;
	private String username;
	private String password;
	private String email;
	
	//dto로 변환
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.username(member.getUsername())
				.password(member.getPassword())
				.email(member.getEmail())
				.build();
		return memberDTO;
	}
	
}
