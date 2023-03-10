package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.entity.Item;

import java.util.List;


public interface CategoryService {

    /**
     * 카테고리 등록
     */
    Category categoryRegister(Category category);

    /**
     * 카테고리 전체 가져오기
     */
    List<Category> getCategoryList();

    /**
     * 카테고리 삭제
     */
    void deleteCategory(Long id);

    /**
     *
     */
    Category getCategory(String categoryName);
}
