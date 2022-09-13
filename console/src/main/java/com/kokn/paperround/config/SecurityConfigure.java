package com.kokn.paperround.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfigure {

//    private final UserService userService;
//    private final LoginService loginService;
//    @Autowired
//    private LoginService loginService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        System.out.println("!!! filterChain !!!");

        http
                .httpBasic() // 특정 resource에 대해 접근시에 브라우저가 사용자에게 username과 password를 확인해 인가를 제한하는 방법 // 매우 심플

            .and()
                .authorizeRequests()
                .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginProcessingUrl("/login-proc");

        return http.build();

    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/assets/**", "h2-console/**", "/api/hello2"));
    }


//    @Bean
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
