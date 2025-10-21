package com.pangapiserver.domain.search;

import com.pangapiserver.application.follow.data.FollowerCountResponse;
import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.search.data.TotalSearchData;
import com.pangapiserver.application.search.data.TotalSearchResponse;
import com.pangapiserver.application.search.enumeration.TotalSearchType;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.user.data.UserListResponse;
import com.pangapiserver.domain.search.repository.SearchCustomRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchCustomRepository searchCustomRepository;
    private final UserService userService;

    public TotalSearchResponse searchByKeyword(String keyword, List<String> chips, UserEntity user) {
        TotalSearchData streamsData = new TotalSearchData(
                user,
                TotalSearchType.STREAMS,
                "title",
                "streams",
                chips,
                null
        );
        List<FollowerCountResponse> followers = userService.getFollowers(user.getId());
        TotalSearchData usersData = new TotalSearchData(
                user,
                TotalSearchType.USERS,
                "username",
                "users",
                null,
                followers
        );
        TotalSearchData productsData = new TotalSearchData(
                user,
                TotalSearchType.PRODUCTS,
                "name",
                "products",
                null,
                null
        );
        List<StreamResponse> streams = searchCustomRepository.searchStreams(keyword, streamsData);
        List<UserListResponse> users = searchCustomRepository.searchUsers(keyword, usersData);
        List<ProductListResponse> products = searchCustomRepository.searchProducts(keyword, productsData);
        return new TotalSearchResponse(
                streams,
                users,
                products
        );
    }
}
