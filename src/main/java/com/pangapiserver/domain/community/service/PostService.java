package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.entity.PostLikeEntity;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.community.repository.PostLikeRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.community.exception.PostNotFoundException;
import com.pangapiserver.domain.community.repository.PostCustomRepository;
import com.pangapiserver.domain.community.repository.PostRepository;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostCustomRepository customRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;

    public PostEntity save(PostEntity entity) {
        return repository.save(entity);
    }

    public Page<PostEntity> getPostsByCommunity(UserEntity user, Long communityId, Pageable pageable, PostFilterType filter) {
        return customRepository.findPostsByCommunity(user, communityId, pageable, filter);
    }

    public PostEntity findById(Long id) {
        return repository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public void togglePostLike(Long postId, UUID userId) {
        PostEntity post = findById(postId);
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        postLikeRepository.findByUser_IdAndPost_Id(user.getId(), post.getId())
            .ifPresentOrElse(
                postLikeRepository::delete,
                () -> {
                    PostLikeEntity newPostLike = PostLikeEntity.builder().user(user).post(post).build();
                    postLikeRepository.save(newPostLike);
                }
            );
    }

    public boolean isPostLikedByUser(Long postId, UUID userId) {
        return postLikeRepository.findByUser_IdAndPost_Id(userId, postId).isPresent();
    }
}
