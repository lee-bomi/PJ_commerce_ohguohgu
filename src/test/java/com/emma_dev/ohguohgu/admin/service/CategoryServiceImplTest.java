package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void register() {
        //given
        Category bone = Category.builder()
                .id(1L)
                .categoryName("뼈간식")
                .build();

        //when
        categoryService.categoryRegister(bone);
        Optional<Category> byId = categoryRepository.findById(1L);
        Category category = byId.get();

        //then
        assertEquals(1L, category.getId());
        assertEquals("뼈간식", category.getCategoryName());

    }

    @Test
    void findAll() {
        //given
        Category bone = Category.builder()
                .id(1L)
                .categoryName("뼈간식")
                .build();

        Category handmade = Category.builder()
                .id(2L)
                .categoryName("수제간식")
                .build();
        categoryRepository.save(bone);
        categoryRepository.save(handmade);
        //when
        List<Category> categories = categoryRepository.findAll();

        //then
        assertEquals(2, categories.size());
    }
}