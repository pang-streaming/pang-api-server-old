package com.pangapiserver.application.category;

import com.pangapiserver.application.category.data.CategoryDto;
import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getCategoryList() {
        return categoryRepository.findAll().stream().map(CategoryDto::of).collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
            .name(categoryDto.name())
            .chip(categoryDto.chip())
            .postImage(categoryDto.postImage())
            .build();
        return CategoryDto.of(categoryRepository.save(categoryEntity));
    }
}
