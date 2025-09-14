package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddCommentRequest;
import com.pangapiserver.application.community.data.CommentResponse;
import com.pangapiserver.domain.community.entity.CommentEntity;
import com.pangapiserver.domain.community.service.CommentService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentUseCaseTest {

    @InjectMocks
    private CommentUseCase commentUseCase;

    @Mock
    private CommentService commentService;

    @Mock
    private UserAuthenticationHolder userAuthHolder;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder().username("testuser").build();
    }

    @Test
    @DisplayName("댓글 생성")
    void addComment() {
        // given
        AddCommentRequest request = new AddCommentRequest(1L, "Test Comment", null);
        given(userAuthHolder.current()).willReturn(user);

        // when
        Response response = commentUseCase.addComment(request);

        // then
        verify(commentService).save(any(CommentEntity.class));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getMessage()).isEqualTo("댓글 작성 성공");
    }

    @Test
    @DisplayName("대댓글 생성")
    void addReplyComment() {
        // given
        AddCommentRequest request = new AddCommentRequest(1L, "Test Reply Comment", 1L);
        CommentEntity parentComment = CommentEntity.builder().id(1L).user(user).build();

        given(userAuthHolder.current()).willReturn(user);
        given(commentService.findById(1L)).willReturn(parentComment);

        // when
        Response response = commentUseCase.addComment(request);

        // then
        verify(commentService).save(any(CommentEntity.class));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getMessage()).isEqualTo("댓글 작성 성공");
    }

    @Test
    @DisplayName("댓글 조회")
    void getComments() {
        // given
        CommentEntity comment1 = CommentEntity.builder().id(1L).user(user).content("comment1").pkPostId(1L).parent(null).build();
        CommentEntity comment2 = CommentEntity.builder().id(2L).user(user).content("comment2").pkPostId(1L).parent(comment1).build();
        List<CommentEntity> comments = List.of(comment1, comment2);

        given(commentService.getCommentsByPostId(1L)).willReturn(comments);

        // when
        DataResponse<List<CommentResponse>> response = commentUseCase.getComments(1L);

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getMessage()).isEqualTo("댓글 조회 성공");
        assertThat(response.getData()).hasSize(1);
        assertThat(response.getData().get(0).children()).hasSize(1);
    }
}
