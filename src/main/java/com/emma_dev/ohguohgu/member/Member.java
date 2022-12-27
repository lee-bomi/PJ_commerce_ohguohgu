package com.emma_dev.ohguohgu.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

    @Id
    private String email;
    private String name;
    private String phone;
    private String password;
    private boolean adminYn;

    private boolean emailAuthYn;
    private String emailAuthKey;

    private LocalDateTime regDt;
    private LocalDateTime udtDt;

}
