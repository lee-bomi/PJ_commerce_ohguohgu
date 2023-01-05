package com.emma_dev.ohguohgu.member.service;

import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;


    @Test
    @DisplayName("회원가입 성공 테스트")
    void register_success() {
        //given
        given(memberRepository.findByUsername(anyString()))
                .willReturn(Optional.of(Member.builder()
                        .username("bomvll@naver.com")
                        .name("이보미")
                        .password("12")
                        .phone("12")
                        .build()));

        //when
        Member member = memberService.checkUser("pobi");

        //then
        verify(memberRepository, times(1)).findByUsername(any());
        assertEquals("이보미", member.getName());
        assertEquals("bomvll@naver.com", member.getUsername());
        assertEquals("12", member.getPhone());
        assertEquals("12", member.getPassword());
    }

    @Test
    @DisplayName("이메일 입력 시 해당 유저정보가 있는지 확인")
    void success_emailAuth() {
        //given
        given(memberRepository.findByEmailAuthKey(anyString()))
                .willReturn(Optional.of(Member.builder()
                        .username("bomvll@naver.com")
                        .name("이보미")
                        .password("12")
                        .phone("12")
                        .emailAuthYn(false)
                        .build()));

        //when
        String uuid = UUID.randomUUID().toString();
        Member member = memberService.emailAuth(uuid);

        //then
        assertEquals("bomvll@naver.com", member.getUsername());
        verify(memberRepository, times(1)).findByEmailAuthKey(any());
        verify(memberRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("이메일 입력 시 해당 유저정보가 있는지 확인")
    void checkUser() {
        //given
        given(memberRepository.findByUsername(anyString()))
                .willReturn(Optional.of(Member.builder()
                        .username("bomvll@naver.com")
                        .name("이보미")
                        .password("12")
                        .phone("12")
                        .emailAuthYn(false)
                        .build()));

        //when
        Member member = memberRepository.findByUsername("bomvll@naver.com").get();

        //then
        assertEquals("이보미", member.getName());
        assertEquals("12", member.getPhone());
        assertEquals("12", member.getPassword());
        verify(memberRepository, times(1)).findByUsername(any());
        verify(memberRepository, times(0)).save(any());
    }
}