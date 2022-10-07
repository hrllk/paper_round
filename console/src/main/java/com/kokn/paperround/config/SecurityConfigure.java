package com.kokn.paperround.config;

import com.kokn.paperround.config.filter.JwtExceptionFilter;
import com.kokn.paperround.config.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("***************************************************************");
        log.debug("* CONFIGURATION - Security Config");

        /***
         * Filter
         */
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, JwtFilter.class);


        http
                /***
                 * Cors Configuration
                 */
                .cors().configurationSource(corsConfigurationSource())

                /***
                 * Cross site Request forgery
                 */
                .and()
                .csrf().disable()

                /***
                 * disable Session
                 * spring security use session basically not use token
                 * so if use token, have to disable session
                 */
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll();
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated(); // 회원가입, 로그인에대해서는 인증을 요하지않는다.

//                .and()
//                .logout()
//                .logoutSuccessUrl("/login");

    }

    /***
     * Cors Configuration Only For develop
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.debug("** Cors Configuration **");
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("encrypt password");
        return new BCryptPasswordEncoder();
    }


}
