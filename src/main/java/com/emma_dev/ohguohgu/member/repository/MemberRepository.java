package com.emma_dev.ohguohgu.member.repository;

import com.emma_dev.ohguohgu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUsername(String email);
    Optional<Member> findByEmailAuthKey(String uuid);
}
