package com.pangapiserver.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import java.util.UUID;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.pangapiserver.domain.user.enumeration.Role;
import com.pangapiserver.domain.user.enumeration.Gender;

@Getter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDate age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String bannerImage;

    private boolean isAdult;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isAlarm;
}