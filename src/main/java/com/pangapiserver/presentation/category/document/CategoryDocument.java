package com.pangapiserver.presentation.category.document;

import com.pangapiserver.application.category.data.CategoryData;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "Category API", description = "카테고리 api")
public interface CategoryDocument {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록을 조회합니다.")
    DataResponse<List<CategoryData>> getCategoryList();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카테고리 등록", description = "카테고리를 등록합니다.")
    DataResponse<CategoryData> createCategory(CategoryData categoryData);
}
