package com.emma_dev.ohguohgu.admin.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    String itemName;
    int price;
    String description;
    Long categoryId;
}
