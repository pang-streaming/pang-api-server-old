package com.pangapiserver.domain.community.entity;

import lombok.Getter;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.pangapiserver.domain.user.entity.UserEntity;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_communities_id", nullable = false)
    private CommunityEntity community;
}