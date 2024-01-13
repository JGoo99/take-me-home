package com.example.takemehome.exception.handler;

import com.example.takemehome.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

  private final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

  @ExceptionHandler(value = UserException.class)
  public ResponseEntity<Map<String, String>> handleUserException(
    UserException e, HttpServletRequest request) {

    HttpHeaders httpHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    LOGGER.error("[handler userException] : {}, {}", request.getRequestURI(), e.getErrorMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", e.getErrorCode().toString());
    map.put("massage", e.getErrorMessage());

    return new ResponseEntity<>(map, httpHeaders, httpStatus);
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, String>> handleException(
    RuntimeException e, HttpServletRequest request) {

    HttpHeaders httpHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    LOGGER.error("[handler exception] : {}, {}", request.getRequestURI(), e.getMessage());

    Map<String, String> map = new HashMap<>();
    map.put("error type", httpStatus.getReasonPhrase());
    map.put("code", "400");
    map.put("massage", e.getMessage());

    return new ResponseEntity<>(map, httpHeaders, httpStatus);
  }
}
