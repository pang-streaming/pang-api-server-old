package com.pangapiserver.domain.user.entity;

import com.pangapiserver.domain.user.enumeration.Gender;
import com.pangapiserver.domain.user.enumeration.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

//TODO: 생성자로 빌더 분리
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
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
    @Column(nullable = false)
    private Role role;

    private boolean isAlarm;

    @Column(columnDefinition = "TEXT")
    private String description;

    public void updateInfo(String nickname, LocalDate age, Gender gender, String profileImage, String bannerImage, String description) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (age != null) {
            this.age = age;
        }
        if (gender != null) {
            this.gender = gender;
        }
        if (profileImage != null) {
            this.profileImage = profileImage;
        }
        if (bannerImage != null) {
            this.bannerImage = bannerImage;
        }
        if (description != null) {
            this.description = description;
        }
    }

    @Builder
    public UserEntity(String username, String email, String nickname, String password, LocalDate age, Gender gender, String profileImage, String bannerImage, boolean isAdult, Role role, boolean isAlarm, String description) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.profileImage = profileImage;
        this.bannerImage = bannerImage;
        this.isAdult = isAdult;
        this.role = role;
        this.isAlarm = isAlarm;
        this.description = description;
    }
}