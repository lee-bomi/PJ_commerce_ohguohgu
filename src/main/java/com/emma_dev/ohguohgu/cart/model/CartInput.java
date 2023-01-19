package com.emma_dev.ohguohgu.cart.model;

import com.emma_dev.ohguohgu.admin.entity.Item;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartInput {

    private Long cartId;
    private Item item;
    private int count;
}
