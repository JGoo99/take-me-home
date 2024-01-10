package com.example.takemehome.exception;

import com.example.takemehome.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserException extends RuntimeException{
  private ErrorCode errorCode;
  private String errorMessage;

  public UserException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }
}
