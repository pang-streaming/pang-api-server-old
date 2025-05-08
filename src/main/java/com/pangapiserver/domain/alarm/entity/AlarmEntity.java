package com.pangapiserver.domain.alarm.entity;

import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alarm")
@Builder
public class AlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID alarm_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;
}
