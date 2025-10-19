package com.pangapiserver.domain.category.service;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.exception.CategoryNotFoundException;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryEntity getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
