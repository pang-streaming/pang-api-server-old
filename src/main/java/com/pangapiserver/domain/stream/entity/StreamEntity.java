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
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(name = "fk_category_id")
    private CategoryEntity category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stream_tags", joinColumns = @JoinColumn(name = "stream_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StreamStatus status;

    private String thumbnail;

    @Builder
    public StreamEntity(UserEntity user, CategoryEntity category, String title, String url, LocalDateTime startAt, List<String> tags, String thumbnail) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.url = url;
        this.startAt = startAt;
        this.tags = tags;
        this.status = StreamStatus.LIVE;
        this.thumbnail = thumbnail;
    }

    public void updateEndAt() {
        this.endAt = LocalDateTime.now();
        this.status = StreamStatus.ENDED;
        this.url = this.url.replace("playlist.m3u8", "master.m3u8");
    }

    public void updateStream(CategoryEntity category, String title, List<String> tags, String thumbnail) {
        this.category = category;
        this.title = title;
        this.tags = tags;
        this.thumbnail = thumbnail;
    }
}
