package com.emma_dev.ohguohgu.member;

import com.emma_dev.ohguohgu.MailComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MailComponent mailComponent;

    @Override
    public boolean register(MemberInput input) {

        Optional<Member> optionalMember = memberRepository.findByEmail(input.email);
        System.out.println("member : " + optionalMember);
        if (optionalMember.isPresent()) {
            return false;
        }

        String encPw = BCrypt.hashpw(input.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        memberRepository.save(Member.builder()
                .email(input.email)
                .name(input.name)
                .phone(input.phone)
                .password(encPw)
                .adminYn(AdminYn.FALSE)
                .emailAuthYn(EmailAuthYn.FALSE)
                .emailAuthKey(uuid)
                .regDt(LocalDateTime.now())
                .build());

        String email = input.getEmail();
        String subject = "오구오구 가입 인증메일입니다";
        String text = "<p>가입축하드려요! 아래링크 클릭하셔서 가입을 완료하세요</p>"
                + "<div><a href='http://localhost:8080/member/email-auth?uuid=" + uuid + "'>가입인증하기</a></div>";

        mailComponent.sendMail(email, subject, text);

        return true;
    }
}
