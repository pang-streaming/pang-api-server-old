package com.pangapiserver.application.category.data;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.enumeration.Chip;
import lombok.Builder;

@Builder
public record CategoryData(
        Long id,
        String name,
        Chip chip,
        String postImage,
        Long streamCount
) {
    public static CategoryData of(CategoryEntity categoryEntity) {
        return CategoryData.builder()
            .id(categoryEntity.getId())
            .name(categoryEntity.getName())
            .chip(categoryEntity.getChip())
            .postImage(categoryEntity.getPostImage())
            .streamCount((long) (categoryEntity.getStreams() == null ? 0 : categoryEntity.getStreams().size()))
            .build();
    }
}
