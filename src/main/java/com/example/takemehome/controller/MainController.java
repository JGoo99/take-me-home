package com.example.takemehome.controller;

import com.example.takemehome.data.dto.user.MemberDto;
import com.example.takemehome.service.impl.UserMemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

  private final UserMemberServiceImpl userMemberService;
  private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

  @GetMapping("/")
  public String mainP() {

    return "home";
  }

  @GetMapping("/join")
  public String joinP(Model model) {

    model.addAttribute("errors", new HashMap<>());

    return "join";
  }

  @PostMapping("/joinProc")
  public String joinProcP(@Valid @ModelAttribute MemberDto memberDto,
                          BindingResult bindingResult, Model model) {
    LOGGER.info("[user join]: {}", memberDto.toString());

    if (bindingResult.hasErrors()) {
      Map<String, String> errors =
        userMemberService.getValidExceptionResult(bindingResult.getAllErrors());
      model.addAttribute("errors", errors);
      return "join";
    }

    userMemberService.join(memberDto);

    return "login";
  }

  @GetMapping("/login")
  public String loginP(Model model,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "exception", required = false) String exception) {

    model.addAttribute("error", error);
    model.addAttribute("exception", exception);

    return "login";
  }

  @GetMapping("/logout")
  public String logoutP(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}
