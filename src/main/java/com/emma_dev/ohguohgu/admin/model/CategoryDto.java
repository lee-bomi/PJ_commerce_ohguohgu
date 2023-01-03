package com.emma_dev.ohguohgu.admin.model;

import com.emma_dev.ohguohgu.admin.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    String categoryName;

    public static List<CategoryDto> of(List<Category> categories) {
        if (categories != null) {
            List<CategoryDto> categoryList = new ArrayList<>();
            for (Category x : categories) {
                categoryList.add(of(x));
            }
            return categoryList;
        }
        return null;
    }

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .categoryName(category.getCategoryName())
                .build();
    }
}
