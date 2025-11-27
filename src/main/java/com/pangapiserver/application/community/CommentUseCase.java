package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddCommentRequest;
import com.pangapiserver.application.community.data.CommentResponse;
import com.pangapiserver.domain.community.entity.CommentEntity;
import com.pangapiserver.domain.community.service.CommentService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class CommentUseCase {

    private final CommentService commentService;
    private final UserAuthenticationHolder userAuthHolder;

    /** 댓글 작성 */
    public Response addComment(AddCommentRequest request) {
        UserEntity user = userAuthHolder.current();

        CommentEntity parent = null;
        if (request.parentId() != null)
            parent = commentService.findById(request.parentId());

        CommentEntity comment = request.toEntity(user, parent);
        commentService.save(comment);

        return Response.ok("댓글 작성 성공");
    }

    /** 댓글 조회 */
    public DataResponse<List<CommentResponse>> getComments(Long postId) {
        List<CommentEntity> comments = commentService.getCommentsByPostId(postId);
        List<CommentResponse> commentResponses = buildCommentTree(comments);
        return DataResponse.ok("댓글 조회 성공", commentResponses);
    }

    private List<CommentResponse> buildCommentTree(List<CommentEntity> comments) {
        Map<Long, CommentResponse> commentMap = comments.stream()
                .collect(Collectors.toMap(
                        CommentEntity::getId,
                        comment -> CommentResponse.fromEntity(comment, new ArrayList<>())
                ));

        return comments.stream()
                .map(comment -> {
                    CommentResponse response = commentMap.get(comment.getId());
                    if (comment.getParent() != null) {
                        commentMap.get(comment.getParent().getId()).children().add(response);
                        return null;
                    }
                    return response;
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
