package com.cos.jpamysqlblogmaven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration          // 빈등록 (IoC 관리)
public class SecurityConfig  {

    // 1. AuthenticationManager 메서드 생성
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 2. Bean 어노테이션은 메서드에[ 붙여서 객체 생성시 사용
    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    // 3. 필터링 설정
    // 해당 메서드가 실행되면 시큐리티 설정이 됨
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1.csrf 토큰 비활성화
        http
                .csrf().disable(); // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)

        // 2.인증 주소 설정
        http
                .authorizeRequests(
                        authorize -> authorize
                                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**") // 누구나 접근 가능
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );

        // 3.로그인 처리 프로세스 설정
        http
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/auth/loginForm")
                                .loginProcessingUrl("/auth/loginProc")
                                .defaultSuccessUrl("/")
                );

        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
//                .authorizeRequests()
//                    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**") // 누구나 접근 가능
//                        .permitAll()
//                    .anyRequest()
//                        .authenticated();
///*
//                .and()
//                    .formLogin()
//                        .loginPage("/auth/loginForm")
//                        .loginProcessingUrl("/auth/loginProc")
//                        .defaultSuccessUrl("/");
//
// */
//    }
}
