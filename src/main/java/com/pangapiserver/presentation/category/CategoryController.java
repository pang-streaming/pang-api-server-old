package com.pangapiserver.presentation.category;

import com.pangapiserver.application.category.CategoryUseCase;
import com.pangapiserver.application.category.data.CategoryDto;
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
    public List<CategoryDto> getCategoryList() {
        return categoryUseCase.getCategoryList();
    }

    @Override
    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryUseCase.createCategory(categoryDto);
    }
}
