package com.pangapiserver.domain.stream.entity;


import com.pangapiserver.domain.user.entity.UserEntity;
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
@Table(name = "streams_keys")
public class StreamKeyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(name = "stream_key", nullable = false, columnDefinition = "TEXT")
    private String key;

    public static StreamKeyEntity create(UserEntity user) {
        return StreamKeyEntity.builder().user(user).build();
    }

    public void updateKey(String key) {
        this.key = key;
    }
}
