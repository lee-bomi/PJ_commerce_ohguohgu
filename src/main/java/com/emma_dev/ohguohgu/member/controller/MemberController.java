package com.emma_dev.ohguohgu.member.controller;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.cart.Service.CartService;
import com.emma_dev.ohguohgu.cart.entity.Cart;
import com.emma_dev.ohguohgu.member.dto.MemberDto;
import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.model.MemberInput;
import com.emma_dev.ohguohgu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final AdminService adminService;
    private final CartService cartService;

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

    @PostMapping("/member/item/add")
    public String addItem(@RequestParam Long id,
                          Model model) {
        // 로그인한 사람 정보가져와서
        UserDetails user = cartService.getUserInfo();
        String loginUser = user.getUsername();
        //해당 아이템을 가져와서
        Item item = cartService.getItem(id);

        //그 사람의 개인카트레포에 담는다
        Cart cart = Cart.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .userName(loginUser)
                .count(1)
                .build();

        cartService.saveCart(cart);

        List<Cart> cartList = cartService.getAllCart(loginUser);
        System.out.println("cart : =============== " + cartList.get(0).getUserName());
        model.addAttribute("cartList", cartList);
        model.addAttribute("username", cartList.get(0).getUserName());
        return "/member/cart";
    }

    //장바구니 메뉴버튼을 이용한 진입
    @GetMapping("member/cart")
    public String cart(Model model) {

        UserDetails user = cartService.getUserInfo();
        String loginUser = user.getUsername();

        List<Cart> cartList = cartService.getAllCart(loginUser);
        model.addAttribute("cartList", cartList);

        return "/member/cart";
    }

    @PostMapping("/member/modify/count")
    public String modifyCount(@RequestParam Long cartId,
                              @RequestParam int count,
                              Model model) {

        Cart cart = cartService.getCart(cartId);
        cart.setCount(count);
        cartService.saveCart(cart);

        UserDetails user = cartService.getUserInfo();
        String loginUser = user.getUsername();

        List<Cart> cartList = cartService.getAllCart(loginUser);
        model.addAttribute("cartList", cartList);

        return "/member/cart";
    }

    @PostMapping("/member/delete/cart")
    public String deleteCartItem(@RequestParam Long cartId,
                                 Model model) {

        System.out.println("======== cartId : " +  cartId);

        Cart cart = cartService.getCart(cartId);
        cartService.deleteItem(cart);

        UserDetails user = cartService.getUserInfo();
        String loginUser = user.getUsername();

        List<Cart> cartList = cartService.getAllCart(loginUser);
        model.addAttribute("cartList", cartList);

        return "/member/cart";
    }
}
