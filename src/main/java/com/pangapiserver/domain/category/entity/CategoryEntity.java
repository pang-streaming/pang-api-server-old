package com.pangapiserver.domain.category.entity;

import com.pangapiserver.domain.category.enumeration.Chip;
import com.pangapiserver.domain.common.entity.BaseEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Chip chip;

    @Column(columnDefinition = "TEXT")
    private String postImage;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StreamEntity> streams = new ArrayList<>();
}
