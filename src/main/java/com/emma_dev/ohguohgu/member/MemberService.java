package com.emma_dev.ohguohgu.member;

public interface MemberService {

    /**
     * 회원가입
     * @param memberInput
     */
    boolean register(MemberInput memberInput);

    /**
     * 이메일인증 확인 로직
     */
    boolean emailAuth(String uuid);
}
