package com.emma_dev.ohguohgu.admin.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInput {

    String itemName;
    int price;
    String description;
}
