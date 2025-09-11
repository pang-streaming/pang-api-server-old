package com.pangapiserver.application.community;

import com.pangapiserver.application.community.data.AddPostRequest;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.community.service.PostService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostUseCaseTest {

    @InjectMocks
    private PostUseCase postUseCase;

    @Mock
    private PostService postService;

    @Mock
    private CommunityService communityService;

    @Mock
    private UserAuthenticationHolder userAuthHolder;

    @Test
    @DisplayName("게시글 생성")
    void addPost() {
        // given
        UserEntity user = UserEntity.builder()
            .username("testuser")
            .build();
        CommunityEntity community = CommunityEntity.builder().id(1L).build();
        AddPostRequest request = new AddPostRequest("Test Title", "Test Content", 1L);

        given(userAuthHolder.current()).willReturn(user);
        given(communityService.findById(request.communityId())).willReturn(community);
        given(postService.save(any(PostEntity.class)))
                .willReturn(PostEntity.builder().id(1L).build());

        // when
        Response response = postUseCase.addPost(request);

        // then
        verify(userAuthHolder).current();
        verify(communityService).findById(request.communityId());
        verify(postService).save(any(PostEntity.class));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getMessage()).isEqualTo("post success");
    }

    @Test
    @DisplayName("게시글 상세보기")
    void getPost() {
        // given
        PostEntity post = PostEntity.builder().id(1L).build();
        given(postService.findById(1L)).willReturn(post);

        // when
        DataResponse<PostEntity> res = postUseCase.getPost(1L);

        // then
        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(res.getData()).isEqualTo(post);
    }
}