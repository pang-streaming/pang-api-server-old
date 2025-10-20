package com.pangapiserver.application.search.data;

import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.user.data.UserListResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public record TotalSearchResponse(
        List<StreamResponse> streams,
        List<UserListResponse> users,
        List<ProductListResponse> products
) { }
