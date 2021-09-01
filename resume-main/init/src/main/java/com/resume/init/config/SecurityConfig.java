package com.resume.init.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/favicon.ico", "/error","/css/**", "/js/**","/images/**", "/jqGrid/**", "/jquery-ui-1.12.1.custom/**");
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests() // 해당 메소드 아래는 각 경로에 따른 권한을 지정할 수 있다.
                .antMatchers("/resources/**").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
                .antMatchers("/").hasRole("ADMIN") // 괄호의 권한을 가진 유저만 접근가능, ROLE_가 붙어서 적용 됨. 즉, 테이블에 ROLE_권한명 으로 저장해야 함.
                .anyRequest().authenticated()  //  로그인된 사용자가 요청을 수행할 떄 필요하다  만약 사용자가 인증되지 않았다면, 스프링 시큐리티 필터는 요청을 잡아내고 사용자를 로그인 페이지로 리다이렉션 해준다.
                .and()
            .formLogin()
                    .loginPage("/login_page")
                    .permitAll()
                    .loginProcessingUrl("/login_proc")
                    .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login_page")
                .invalidateHttpSession(true)
                .and()
            .exceptionHandling()
                 .accessDeniedPage("/accessDenied_page"); // 권한이 없는 대상이 접속을시도했을 때
    }
}
