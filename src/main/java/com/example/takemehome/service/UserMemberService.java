package com.example.takemehome.service;

import com.example.takemehome.data.dto.user.MemberDto;
import com.example.takemehome.data.dto.user.UserInfoDto;

public interface UserMemberService {
  void join(MemberDto memberDto);
}
