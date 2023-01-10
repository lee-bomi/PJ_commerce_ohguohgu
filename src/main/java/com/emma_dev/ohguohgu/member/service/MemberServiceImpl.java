package com.emma_dev.ohguohgu.member.service;

import com.emma_dev.ohguohgu.component.MailComponent;
import com.emma_dev.ohguohgu.member.dto.MemberDto;
import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.model.MemberInput;
import com.emma_dev.ohguohgu.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponent mailComponent;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(@Lazy MemberRepository memberRepository, @Lazy MailComponent mailComponent, @Lazy PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.mailComponent = mailComponent;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberDto register(MemberInput input) {

        Optional<Member> findMember = memberRepository.findByUsername(input.getUsername());
        if (findMember.isPresent()) {
            System.out.println("회원가입 불가 : 이미 존재하는 회원입니다");
            return null;
        }

//        String encPw = passwordEncoder.encode(input.getPassword());
        String encPw = BCrypt.hashpw(input.getPassword(), BCrypt.gensalt());
        System.out.println("encPw : " +  encPw);
        String uuid = UUID.randomUUID().toString();

        Member member = memberRepository.save(Member.builder()
                .username(input.getUsername())
                .name(input.getName())
                .phone(input.getPhone())
                .password(encPw)
                .adminYn(input.getName().equals("admin"))
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .regDt(LocalDateTime.now())
                .build());

        String email = input.getUsername();
        String subject = "오구오구 가입 인증메일입니다";
        String text = "<p>가입축하드려요! 아래링크 클릭하셔서 가입을 완료하세요</p>"
                + "<div><a href='http://localhost:8080/member/email-auth?uuid=" + uuid + "'>가입인증하기</a></div>";

        mailComponent.sendMail(email, subject, text);

        return MemberDto.of(member);
    }

    @Override
    public Member emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if (optionalMember.isEmpty()) {
            return null;
        }

        Member member = optionalMember.get();

//        //이미 계정 활성화되어있는데 새로고침했을때 계속 활성화 되지않게하기 위함
        if (member.isEmailAuthYn()) {
            return null;
        }

        member.setEmailAuthYn(true);
        memberRepository.save(member);

        return member;
    }

    @Override
    public boolean login(MemberInput param) {
        System.out.println("param : " + param);
        Optional<Member> optionalMember = memberRepository.findByUsername(param.getUsername());
        if (optionalMember.isEmpty()) {
            return false;
        }
        System.out.println("optionalMember : " + optionalMember);
        Member member = optionalMember.get();

        String storedPw = member.getPassword();
        String inputPw = param.getPassword();

        boolean matchResult = passwordEncoder.matches(inputPw, storedPw);
        System.out.println("matchResult : " + matchResult);
        if (matchResult) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByUsername(username);

        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다");
        }
        Member member = optionalMember.get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
    }

    @Override
    public Member checkUser(String username) {
        if (username == null) {
            return null;
        }
        Member member = memberRepository.findByUsername(username).get();
        if (member != null) {
            return member;
        } else {
            return null;
        }
    }

    @Override
    public List<Member> getMemberList() {
        return memberRepository.findByAdminYnIsFalse();
    }

}
