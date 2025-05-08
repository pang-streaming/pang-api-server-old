package com.pangapiserver.domain.fcmDevice.entity;

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
@Table(name = "fcmdevice")
@Builder
public class FcmDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Boolean is_alarm;
}
