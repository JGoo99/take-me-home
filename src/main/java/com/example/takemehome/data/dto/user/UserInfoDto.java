package com.example.takemehome.data.dto.user;

import com.example.takemehome.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserInfoDto {
  private String username;
  private Integer score;

  public static UserInfoDto from(User user) {
    return UserInfoDto.builder()
      .username(user.getUsername())
      .score(user.getScore())
      .build();
  }
}
