package com.pangapiserver.domain.category.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "main_category")
@NoArgsConstructor
@AllArgsConstructor
public class MainCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
