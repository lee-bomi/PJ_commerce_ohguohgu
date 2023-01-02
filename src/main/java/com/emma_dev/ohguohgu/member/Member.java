package com.emma_dev.ohguohgu.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

    @Id
    private String username;
    private String name;
    private String phone;
    private String password;
    private boolean adminYn;

    private boolean emailAuthYn;
    private String emailAuthKey;

    private LocalDateTime regDt;
    private LocalDateTime udtDt;

}
