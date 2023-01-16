package com.emma_dev.ohguohgu.member.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.member.dto.MemberDto;
import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface MemberService extends UserDetailsService {

    /**
     * 회원가입
     * @param memberInput
     */
    MemberDto register(MemberInput memberInput);

    /**
     * 이메일인증 확인 로직
     */
    Member emailAuth(String uuid);

    /**
     * 로그인
     */
    boolean login(MemberInput param);

    /**
     * 시큐리티 - 회원정보 가져오기
     */
    UserDetails loadUserByUsername(String email);

    /**
     * 존재하는 회원인지 확인
     */
    Member checkUser(String username);

    /**
     * 전체 회원 리스트(관리자 제외)
     */
    List<Member> getMemberList();

    /**
     * 장바구니에 상품 추가
     */
    Item addItem(Item item);
}
