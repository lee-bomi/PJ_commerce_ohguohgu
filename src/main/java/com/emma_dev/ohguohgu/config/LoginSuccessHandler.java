package com.emma_dev.ohguohgu.config;

import com.emma_dev.ohguohgu.member.dto.MemberDto;
import com.emma_dev.ohguohgu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberService memberService;
    private static final int TIME = 60 * 60 * 24;
    private final RequestCache reqCache = new HttpSessionRequestCache();    //클라이언트가 접근하고자했던 자원의 정보를 담고있음
    private static final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setMaxInactiveInterval(TIME);

        if (username.equals("emmababy@kakao.com")) {    //관리자아이디 : "emmababy@kakao.com"
            redirectStrategy.sendRedirect(request, response, "/admin/main.do");
        } else {
//            resultRedirectStrategy(request, response, authentication);
            //관리자가 아닌경우
            redirectStrategy.sendRedirect(request, response, "/member/item/list");
        }

    }

    //로그인 실패할 경우 핸들러
//    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
//                                          Authentication authentication) throws IOException, ServletException {
//        SavedRequest savedRequest = reqCache.getRequest(request, response);
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//        } else {
//            redirectStrategy.sendRedirect(request, response, "/member/login");
//        }
//    }

}
