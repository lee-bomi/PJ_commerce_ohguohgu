package com.emma_dev.ohguohgu.member.dto;

import com.emma_dev.ohguohgu.member.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    String username;
    String name;
    String phone;
    String password;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .phone(member.getPhone())
                .password(member.getPassword())
                .build();
    }
}
