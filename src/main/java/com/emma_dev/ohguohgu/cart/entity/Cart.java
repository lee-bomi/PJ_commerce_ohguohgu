package com.emma_dev.ohguohgu.cart.entity;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer count;

    private String userName;

}
