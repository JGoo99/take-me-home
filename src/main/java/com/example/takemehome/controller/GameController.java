package com.example.takemehome.controller;

import com.example.takemehome.data.dto.user.AnswerDto;
import com.example.takemehome.service.impl.UserMemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

  private final UserMemberServiceImpl userMemberService;
  private final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

  @GetMapping("/game")
  public String gameP() {

    return "game";
  }

  @PostMapping("/do")
  public String testP(@ModelAttribute AnswerDto answer, HttpSession session) {

    LOGGER.info(answer.getAnswerPitch().toString());
    LOGGER.info(answer.getMyPitch().toString());

    boolean isComplete = userMemberService.isComplete(answer);
    session.setAttribute("isComplete", isComplete);

    return "redirect:/result";
  }

  @GetMapping("/result")
  public String resultP(HttpSession session, Model model) {

    model.addAttribute("isComplete",
      session.getAttribute("isComplete"));

    return "game-result";
  }
}
