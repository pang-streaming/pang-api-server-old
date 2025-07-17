package com.pangapiserver.domain.stream.entity;

import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "streams")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class StreamEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime startedAt;

    private LocalDateTime endAt;

    @Builder
    public StreamEntity(UserEntity user, String title, String url) {
        this.user = user;
        this.title = title;
        this.url = url;
    }

    public void updateEndAt() {
        this.endAt = LocalDateTime.now();
    }
}
