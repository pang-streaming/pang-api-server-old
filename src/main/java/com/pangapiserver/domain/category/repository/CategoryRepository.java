package com.pangapiserver.domain.category.repository;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryCustomRepository {
    Optional<CategoryEntity> findByName(String name);
}
