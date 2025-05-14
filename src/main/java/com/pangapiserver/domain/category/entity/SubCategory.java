package com.pangapiserver.domain.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_category")
public class SubCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_main_category", nullable = false)
    private MainCategory mainCategory;

    @Column(columnDefinition = "TEXT")
    private String postImage;
}
