package com.example.takemehome.service.impl;

import com.example.takemehome.data.dto.user.AnswerDto;
import com.example.takemehome.data.dto.user.MemberDto;
import com.example.takemehome.data.entity.User;
import com.example.takemehome.exception.UserException;
import com.example.takemehome.repository.UserRepository;
import com.example.takemehome.service.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.takemehome.type.ErrorCode.Duplicate_Username;
import static com.example.takemehome.type.ErrorCode.User_NotFound;

@Service
@RequiredArgsConstructor
public class UserMemberServiceImpl implements UserMemberService {

  private final UserRepository userRepository;
  private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Override
  public void join(MemberDto memberDto) {

    validateUsernameUniqueness(memberDto.getUsername());
    setEncodedPassword(memberDto);

    userRepository.save(MemberDto.toEntity(memberDto));
  }

  @Override
  public boolean isComplete(AnswerDto answerDto) {

    int gap = Math.abs(answerDto.getMyPitch() - answerDto.getAnswerPitch());

    return (gap <= 2);
  }

  public void setEncodedPassword(MemberDto memberDto) {
    memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
  }

  public void validateUsernameUniqueness(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new UserException(Duplicate_Username);
    }
  }

  public User findByUserId(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new UserException(User_NotFound));
  }

  public Map<String, String> getValidExceptionResult(List<ObjectError> allErrors) {
    Map<String, String> errors = new HashMap<>();

    allErrors.forEach(m -> errors.put(((FieldError) m).getField(), m.getDefaultMessage()));
    return errors;
  }
}
