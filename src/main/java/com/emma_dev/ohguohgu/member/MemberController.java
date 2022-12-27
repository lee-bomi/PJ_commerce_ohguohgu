package com.emma_dev.ohguohgu.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register(){

        return "/member/register";
    }

    @PostMapping("/member/register")
    public String registSubmit(Model model, HttpServletRequest request, MemberInput input){
        boolean result = memberService.register(input);
        if (!result) {
            return "/member/register_fail";
        }
        model.addAttribute("result", result);
        return "/member/register_complete";
    }

        memberService.register(input);

        return "redirect:/";
    }
}
