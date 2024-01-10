package com.example.takemehome.exception.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage;
    if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
      errorMessage = "아이디/비밀번호가 맞지 않거나, 아직 회원가입되지 않았습니다.";
    } else {
      errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
    }
    errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
    setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

    super.onAuthenticationFailure(request, response, exception);
  }
}
