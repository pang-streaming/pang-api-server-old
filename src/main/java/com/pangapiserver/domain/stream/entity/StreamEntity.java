package com.pangapiserver.domain.stream.entity;

import lombok.Getter;
import lombok.Builder;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.pangapiserver.domain.user.entity.UserEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "streams")
public class StreamEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = true)
    private LocalDateTime endAt;
}
