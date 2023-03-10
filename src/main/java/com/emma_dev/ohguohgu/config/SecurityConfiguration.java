
package com.emma_dev.ohguohgu.config;

import com.emma_dev.ohguohgu.member.model.MemberInput;
import com.emma_dev.ohguohgu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private final MemberService memberService;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/"
                        , "/member/login"
                        , "/member/register"
                        , "/member/email-auth"
                        , "/member/find-password"
                        , "/member/reset/password"
                )
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/admin/**", "/swagger-ui/**")
                .hasAuthority("ROLE_ADMIN")
                .antMatchers("/member/**")
                .hasAuthority("ROLE_USER");

        http.formLogin()    //인증이 필요할때 이동할 페이지
                .loginPage("/member/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(getFailureHandler())
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        http.csrf().disable();  //post요청이 가능하도록 추가 설정함
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService) //인증할때 회원의 정보를 넘겨야해서
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);

    }


    //authentication : 로그인
    //authorization : 권한





}
