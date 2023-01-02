package com.emma_dev.ohguohgu.member.controller;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.member.model.MemberInput;
import com.emma_dev.ohguohgu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final AdminService adminService;

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

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("uuid");
        //service에서 id정보확인
        boolean authResult = memberService.emailAuth(uuid);
        model.addAttribute("result", authResult);

        return "member/emailAuthResult";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(MemberInput param) {

        boolean result = memberService.login(param);
        System.out.println("result : " + result);
        if(result) {
            return "member/successLogin";
        } else {
            return "member/login_fail";
        }
    }

    @GetMapping("/member/loginSuccess")
    public String loginSuccess() {
        return "index";
    }

    @GetMapping("/member/item/list")
    public String itemList(Model model) {
        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/member/itemList";
    }
}
