package com.emma_dev.ohguohgu.admin.entity;

import com.emma_dev.ohguohgu.admin.model.ItemDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Integer price;
    private String description;

    public static Item from(ItemDto itemDto) {
        return Item.builder()
                .itemName(itemDto.getItemName())
                .price(itemDto.getPrice())
                .description(itemDto.getDescription())
                .build();
    }
}
