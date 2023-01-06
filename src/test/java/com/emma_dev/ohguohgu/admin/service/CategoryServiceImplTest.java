package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import com.emma_dev.ohguohgu.admin.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("관리자 - 카테고리등록 ")
    void register() {
        //given
        Category bone = Category.builder()
                .categoryId(1L)
                .categoryName("뼈간식")
                .build();

        //when
        categoryService.categoryRegister(bone);
        Optional<Category> byId = categoryRepository.findById(1L);
        Category _category = byId.get();

        //then
        assertEquals(1L, _category.getCategoryId());
        assertEquals("뼈간식", _category.getCategoryName());

    }

    @Test
    void findAll() {
        //given
        Category bone = Category.builder()
                .categoryId(1L)
                .categoryName("뼈간식")
                .build();

        Category handmade = Category.builder()
                .categoryId(2L)
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