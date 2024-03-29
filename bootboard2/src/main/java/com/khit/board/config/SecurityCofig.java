package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityCofig {
	
	@Autowired
	private CustomUserDetailsService customService;
	
	//@Bean는 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할때 필요함
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//인증 설정 -> 권한 설정
		//로그인이 필요없음 .permitAll() : "/", "/css/**", "images/**", "/auth/main", ".member/**"
		//로그인이 필요 authenticated(): 그외의 경로
		http.userDetailsService(customService);
		
		http 
		 .authorizeHttpRequests(authorize -> authorize
				 .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/auth/main" ).permitAll()
				 .requestMatchers("/board/write").authenticated()
				 .requestMatchers("/member/**", "/board/**").permitAll()
				 .anyRequest().authenticated()
				 )
		 		 .formLogin(form -> 
		 		 	form.loginPage("/member/login")
		 		 	    .defaultSuccessUrl("/") //로그인 성공 시 "/" url로 이동
		 				   );
		
		http.logout(logout ->logout.logoutUrl("/member/logout")
		 		 .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
		 		 .invalidateHttpSession(true) //세션 무효화
		 		 .logoutSuccessUrl("/"));
		
		return http.build();
	}
	
	//암호화 설정
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
