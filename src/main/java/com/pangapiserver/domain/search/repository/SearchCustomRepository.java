package com.pangapiserver.domain.search.repository;

import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.search.data.TotalSearchData;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.user.data.UserListResponse;

import java.util.List;

public interface SearchCustomRepository {
    List<StreamResponse> searchStreams(String keyword, TotalSearchData data);
    List<UserListResponse> searchUsers(String keyword, TotalSearchData data);
    List<ProductListResponse> searchProducts(String keyword, TotalSearchData data);
}
