package com.pangapiserver.domain.watchHistory.entity;

import com.pangapiserver.domain.common.entity.BaseEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "watch_histories")
@NoArgsConstructor
public class WatchHistoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stream_id", nullable = false)
    private StreamEntity stream;

    @Builder
    public WatchHistoryEntity(UserEntity user, StreamEntity stream) {
        this.user = user;
        this.stream = stream;
    }
}
