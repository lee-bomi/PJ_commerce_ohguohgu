package com.emma_dev.ohguohgu.cart.entity;

import com.emma_dev.ohguohgu.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne(mappedBy = "cart")
    private Member member;

}
