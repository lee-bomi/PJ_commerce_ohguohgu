package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import com.emma_dev.ohguohgu.admin.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category categoryRegister(Category _category) {
        if (categoryRepository.countAllByCategoryName(_category.getCategoryName()) > 0) {
            throw new RuntimeException("해당 카테고리는 이미 존재합니다");
        }

        return categoryRepository.save(_category);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            return;
        }
        Category category = byId.get();

        categoryRepository.deleteById(category.getCategoryId());
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("해당 카테고리는 없어요"));

    }
}
