package com.pangapiserver.domain.stream.entity;

import com.pangapiserver.domain.common.entity.BaseEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "temp_streams")
@NoArgsConstructor
public class TempStreamEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column(columnDefinition = "TEXT")
    private String webRtcUrl;

    @Column(columnDefinition = "TEXT")
    private String webRtcPlaybackUrl;

    private String streamName;

    private Boolean isLive = false;

    @Builder
    public TempStreamEntity(UserEntity user, String uid, String webRtcUrl, String webRtcPlaybackUrl, String streamName) {
        this.user = user;
        this.uid = uid;
        this.webRtcUrl = webRtcUrl;
        this.webRtcPlaybackUrl = webRtcPlaybackUrl;
        this.streamName = streamName;
    }

    public void updateStreamInfo(String streamName, Boolean isLive) {
        if (streamName != null) {
            this.streamName = streamName;
        }
        if (isLive != null) {
            this.isLive = isLive;
        }
    }
}
