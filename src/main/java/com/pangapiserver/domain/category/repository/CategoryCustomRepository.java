package com.pangapiserver.domain.category.repository;

import com.pangapiserver.application.category.data.CategoryData;

import java.util.List;

public interface CategoryCustomRepository {
    List<CategoryData> findAllWithStreamCount();
}
