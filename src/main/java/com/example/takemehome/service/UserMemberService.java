package com.example.takemehome.service;

import com.example.takemehome.data.dto.user.AnswerDto;
import com.example.takemehome.data.dto.user.MemberDto;

public interface UserMemberService {
  void join(MemberDto memberDto);
  boolean isComplete(AnswerDto answerDto);
}
