package com.example.takemehome.data.dto.user;

import com.example.takemehome.data.entity.User;
import com.example.takemehome.type.RoleType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MemberDto {

  @Column(unique = true)
  @NotNull(message = "아이디는 필수입니다.")
  @Size(min = 3, max = 18, message = "최소 3자 이상 최대 18자 이하를 입력하시오.")
  private String username;

  @NotNull(message = "비밀번호는 필수입니다.")
  @Size(min = 3, message = "최소 3자 이상을 입력하시오.")
  private String password;

  public static User toEntity(MemberDto memberDto) {
    return User.builder()
      .username(memberDto.getUsername())
      .password(memberDto.getPassword())
      .role(RoleType.ROLE_USER.toString())
      .build();
  }
}
