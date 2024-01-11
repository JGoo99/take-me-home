package com.example.takemehome.data.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AnswerDto {
  private Integer myPitch;
  private Integer answerPitch;
}
