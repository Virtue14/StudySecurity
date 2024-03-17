package akduck.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 동작 순서는 상단부터 동작
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "login").permitAll()  // 모든 사용자 접근가능
                .requestMatchers("/admin").hasRole("ADMIN")     // 특정 권한 부여
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()   // 로그인한 모든 사용자 접근가능

        );


        return httpSecurity.build();

    }
}