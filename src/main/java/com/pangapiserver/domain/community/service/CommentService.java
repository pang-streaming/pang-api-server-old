package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.CommentEntity;
import com.pangapiserver.domain.community.exception.CommentNotfoundException;
import com.pangapiserver.domain.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    public CommentEntity findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotfoundException::new);
    }

    public List<CommentEntity> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPkPostIdOrderByCreatedAtAsc(postId);
    }
}
