package com.emma_dev.ohguohgu.member.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInput {

    String username;
    String name;
    String phone;
    String password;
}
