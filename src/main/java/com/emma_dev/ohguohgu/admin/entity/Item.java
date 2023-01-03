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
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;
    private Integer price;
    private String description;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category categoryId;

    public static Item from(ItemDto itemDto) {
        return Item.builder()
                .itemName(itemDto.getItemName())
                .price(itemDto.getPrice())
                .description(itemDto.getDescription())
                .categoryId(Category.builder()
                        .categoryId(itemDto.getCategoryId())
                        .build())
                .build();
    }
}
