package com.example.takemehome.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  User_NotFound("해당 유저 정보를 찾을 수 없습니다."),
  Duplicate_Username("해당 닉네임은 이미 존재합니다."),
  PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다");

  private final String description;
}
