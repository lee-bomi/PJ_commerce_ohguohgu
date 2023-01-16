package com.emma_dev.ohguohgu.member.controller;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.member.dto.MemberDto;
import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.model.MemberInput;
import com.emma_dev.ohguohgu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        MemberDto result = memberService.register(input);
        if (result == null) {
            return "/member/register_fail";
        }
        model.addAttribute("result", result);
        return "/member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("uuid");
        //service에서 id정보확인
        Member authResult = memberService.emailAuth(uuid);
        model.addAttribute("result", authResult);

        return "member/emailAuthResult";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }



    @PostMapping("/member/login")
    public void loginSubmit(MemberInput input) {
        System.out.println("isAuthenticated : " + isAuthenticated());

        //시큐리티에서 처리
//        boolean loginResult = memberService.login(input);

//        if (!loginResult) {
//            return "/member/login?error=true";
//        }
//        return "/member/itemList";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
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

    @GetMapping("/member/itemAdd")
    public String itemAdd(Model model, Long id) {
        Item item = adminService.getItem(id);


        return "/member/itemList";
    }
}
