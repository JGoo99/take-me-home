package com.example.takemehome.repository;

import com.example.takemehome.data.dto.user.MemberDto;
import com.example.takemehome.data.entity.User;
import com.example.takemehome.exception.UserException;
import com.example.takemehome.type.ErrorCode;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Spy
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Test
  void save() {
    // given
    MemberDto memberDto = getMemberDto();
    memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));

    // when
    User user = userRepository.save(MemberDto.toEntity(memberDto));

    // then
    assertEquals(memberDto.getUsername(), user.getUsername());
  }

  @Test
  void findByUsername() {
    // given
    MemberDto memberDto = getMemberDto();
    String rawPW = memberDto.getPassword();
    memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
    userRepository.save(MemberDto.toEntity(memberDto));

    // when
    User selectedUser = userRepository.findByUsername(memberDto.getUsername())
      .orElseThrow(() -> new UserException(ErrorCode.User_NotFound));

    // then
    assertEquals(memberDto.getUsername(), selectedUser.getUsername());
    assertTrue(bCryptPasswordEncoder.matches(rawPW, selectedUser.getPassword()));
  }

  @Test
  void existByUsername() {
    // given
    MemberDto memberDto = getMemberDto();
    String rawPW = memberDto.getPassword();
    memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
    User user = userRepository.save(MemberDto.toEntity(memberDto));

    // when
   boolean isUser = userRepository.existsByUsername(memberDto.getUsername());

    // then
    assertTrue(isUser);
  }

  User getUserEntity() {
    return User.builder()
      .username("goo")
      .build();
  }

  MemberDto getMemberDto() {
    return MemberDto.builder()
      .username("goo")
      .password("123")
      .build();
  }
}