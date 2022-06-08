package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/register").permitAll()
                .mvcMatchers("/quiz/names").permitAll()
                .mvcMatchers("/allQuizzes").permitAll()
                .mvcMatchers("/api/auth/verify/{userName}").permitAll()
                .mvcMatchers("/password/forgot/{email}").permitAll()
                .mvcMatchers("/api/auth/change/password/{password}").permitAll()
                .mvcMatchers("/support/{name}/{text}").permitAll()
                .mvcMatchers("/resend/email/{email}").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                );

        //http.headers().frameOptions().disable();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }

        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
