package com.pangapiserver.application.category;

import com.pangapiserver.application.category.data.CategoryData;
import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DataResponse<List<CategoryData>> getCategoryList() {
        return DataResponse.ok("카테고리 목록 조회 성공", categoryRepository.findAllWithStreamCount());
    }

    public DataResponse<CategoryData> createCategory(CategoryData categoryData) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
            .name(categoryData.name())
            .chip(categoryData.chip())
            .postImage(categoryData.postImage())
            .build();
        return DataResponse.ok("카테고리 생성", CategoryData.of(categoryRepository.save(categoryEntity)));
    }
}
