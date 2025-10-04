package com.pangapiserver.application.category;

import com.pangapiserver.application.category.data.CategoryData;
import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public List<CategoryData> getCategoryList() {
        return categoryRepository.findAllWithStreamCount();
    }

    public CategoryData createCategory(CategoryData categoryData) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
            .name(categoryData.name())
            .chip(categoryData.chip())
            .postImage(categoryData.postImage())
            .build();
        return CategoryData.of(categoryRepository.save(categoryEntity));
    }
}
