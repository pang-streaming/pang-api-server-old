package com.pangapiserver.domain.stream.entity;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.common.entity.BaseEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "streams")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class StreamEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    private LocalDateTime endAt;

    @Builder
    public StreamEntity(UserEntity user, CategoryEntity category, String title, String url) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.url = url;
    }

    public void updateEndAt() {
        this.endAt = LocalDateTime.now();
    }
}
