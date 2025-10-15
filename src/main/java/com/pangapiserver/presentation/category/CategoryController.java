package com.pangapiserver.presentation.category;

import com.pangapiserver.application.category.CategoryUseCase;
import com.pangapiserver.application.category.data.CategoryData;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.presentation.category.document.CategoryDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController implements CategoryDocument {

    private final CategoryUseCase categoryUseCase;

    @Override
    @GetMapping
    public DataResponse<List<CategoryData>> getCategoryList() {
        return categoryUseCase.getCategoryList();
    }

    @Override
    @PostMapping
    public DataResponse<CategoryData> createCategory(@RequestBody CategoryData categoryData) {
        return categoryUseCase.createCategory(categoryData);
    }
}
