package com.jobtang.sourcecompany.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf 검증 비활성화
        http.csrf().disable();
        http.formLogin().disable();
        // basic한 방법은 거절. 보통 아는 bearer 방식을 사용
//        http.httpBasic().disable();
        http.httpBasic().disable()
                .authorizeRequests()// 요청에 대한 사용권한 체크
    //                .antMatchers(HttpMethod.GET, "/**").permitAll() // <- 특정 메서드에서만 반응시키고싶으면 앞에 메서드적기
//                    .antMatchers("/test")
//                    .hasRole("MEMBER")
    //                .authenticated() // 인증된 유저만 허용
    //                .access("hasRole('MEMBER')") // hasRole은 1개만 되기 때문에, 복수 권한 허용은 access를 써서 표현식 사용
    //                .antMatchers("/admin/**").hasRole("ADMIN")
    //                .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/**").permitAll() // 모든 url에 대해서 일단 허용
                    .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        // 세션 미사용 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
