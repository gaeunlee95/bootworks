package com.khit.test.entity;

import com.khit.test.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "table_member")
public class Member {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String username;

@Column(nullable = false)
private String password;

@Column
private String email;

//entity로 변환
public static Member toSaveEntity(MemberDTO memberDTO) {
	Member member = Member.builder()
			.username(memberDTO.getUsername())
			.password(memberDTO.getPassword())
			.email(memberDTO.getEmail())
			.build();
	return member;
	}
}