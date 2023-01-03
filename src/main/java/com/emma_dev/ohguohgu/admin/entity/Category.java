package com.emma_dev.ohguohgu.admin.entity;

import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;


    public static Category from(CategoryInput input) {
        return Category.builder()
                .categoryName(input.getCategoryName())
                .build();
    }
}
