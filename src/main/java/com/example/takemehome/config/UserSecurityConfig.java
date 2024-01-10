package com.example.takemehome.config;

import com.example.takemehome.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  private final AuthenticationFailureHandler userLoginFailureHandler;

  @Bean
  public PasswordEncoder userBCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider userAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(userBCryptPasswordEncoder());
    return provider;
  }


  @Bean
  public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf((auth) -> auth.disable());

    http
      .authenticationProvider(userAuthenticationProvider())
      .authorizeHttpRequests((auth) -> auth
        .requestMatchers("/static/**", "/js/**", "/img/**", "/css/**").permitAll()
        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
        .requestMatchers("/game", "/do", "/result", "/logout").hasRole("USER")
        .anyRequest().permitAll()
      );

    http
      .formLogin((auth) -> auth
        .loginPage("/login")
        .loginProcessingUrl("/loginProc")
        .failureHandler(userLoginFailureHandler)
        .defaultSuccessUrl("/game")
      )
      .logout((auth) -> auth
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
      );

    return http.build();
  }
}
