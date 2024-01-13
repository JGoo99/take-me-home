package com.example.takemehome.controller;

import com.example.takemehome.data.dto.user.AnswerDto;
import com.example.takemehome.data.dto.user.CustomUserDetails;
import com.example.takemehome.data.dto.user.RankingPageDto;
import com.example.takemehome.data.dto.user.UserInfoDto;
import com.example.takemehome.service.impl.UserMemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GameController {

  private final UserMemberServiceImpl userMemberService;
  private final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

  @GetMapping("/game")
  public String gameP(@AuthenticationPrincipal CustomUserDetails details, Model model) {

    int score = userMemberService.getScore(details.getId());
    model.addAttribute("score", score);

    return "game";
  }

  @PostMapping("/do")
  public String testP(@ModelAttribute AnswerDto answer, HttpSession session,
                      @AuthenticationPrincipal CustomUserDetails details) {

    boolean isComplete = userMemberService.isComplete(answer);
    if (isComplete) {
      userMemberService.addOnePoint(details.getId());
    }

    session.setAttribute("isComplete", isComplete);

    return "redirect:/result";
  }

  @GetMapping("/result")
  public String resultP(@AuthenticationPrincipal CustomUserDetails details,
                        HttpSession session, Model model) {

    int score = userMemberService.getScore(details.getId());

    model.addAttribute("isComplete", session.getAttribute("isComplete"));
    model.addAttribute("score", score);

    return "game-result";
  }

  @GetMapping("/ranking")
  public String homeP(@RequestParam(defaultValue = "1") int page,
                      @ModelAttribute RankingPageDto pageDto,
                      @PageableDefault(page = 1) Pageable pageable, Model model) {

    pageDto.setPageNum(page);
    Page<UserInfoDto> list = userMemberService.getRankingList(pageDto);
    LOGGER.info(String.valueOf(list.isEmpty()));
    pageDto.setPaging(pageable, list.getTotalPages());

    model.addAttribute("list", list);
    model.addAttribute("startPage", pageDto.getStartPage());
    model.addAttribute("endPage", pageDto.getEndPage());;
    model.addAttribute("page", page);

    return "ranking";
  }

}
