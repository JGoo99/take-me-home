package com.example.takemehome.service;

import com.example.takemehome.data.dto.user.AnswerDto;
import com.example.takemehome.data.dto.user.MemberDto;
import com.example.takemehome.data.dto.user.RankingPageDto;
import com.example.takemehome.data.dto.user.UserInfoDto;
import org.springframework.data.domain.Page;

public interface UserMemberService {
  void join(MemberDto memberDto);
  boolean isComplete(AnswerDto answerDto);

  void addOnePoint(Long userId);

  int getScore(Long userId);

  Page<UserInfoDto> getRankingList(RankingPageDto pageDto);
}
