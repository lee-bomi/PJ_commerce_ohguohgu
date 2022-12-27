package com.emma_dev.ohguohgu.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput input) {

        Optional<Member> optionalMember = memberRepository.findById(input.email);
        if (!optionalMember.isPresent()) {
            return false;
        }

        Member.builder()
                .email(input.email)
                .name(input.name)
                .phone(input.phone)
                .password(input.password)
                .build();
        return true;
    }
}
