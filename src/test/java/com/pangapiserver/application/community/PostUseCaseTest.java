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
import com.pangapiserver.application.community.data.PostListResponse;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

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

    private UserEntity user;
    private CommunityEntity community;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder().username("testuser").build();
        community = CommunityEntity.builder().id(1L).build();
    }

    @Test
    @DisplayName("게시글 생성")
    void addPost() {
        // given
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

    @Test
    @DisplayName("커뮤니티별 게시글 목록 조회")
    void getPostsByCommunity() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        List<PostEntity> posts = List.of(
            PostEntity.builder().id(1L).title("title1").content("content1").user(user).community(community).build(),
            PostEntity.builder().id(2L).title("title2").content("content2").user(user).community(community).build()
        );
        Page<PostEntity> postPage = new PageImpl<>(posts, pageable, posts.size());

        given(userAuthHolder.current()).willReturn(user);
        given(postService.getPostsByCommunity(user, 1L, pageable, PostFilterType.ALL)).willReturn(postPage);

        // when
        DataResponse<Page<PostListResponse>> response = postUseCase.getPostsByCommunity(1L, pageable, PostFilterType.ALL);

        // then
        verify(userAuthHolder).current();
        verify(postService).getPostsByCommunity(user, 1L, pageable, PostFilterType.ALL);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getData().getTotalElements()).isEqualTo(2);
        assertThat(response.getData().getContent().get(0).title()).isEqualTo("title1");
    }
}