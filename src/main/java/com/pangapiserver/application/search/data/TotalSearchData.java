package com.pangapiserver.application.search.data;

import com.pangapiserver.application.follow.data.FollowerCountResponse;
import com.pangapiserver.application.search.enumeration.TotalSearchType;
import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;

public record TotalSearchData(
        UserEntity user,
        TotalSearchType type,
        String field,
        String index,
        List<String> chips,
        List<FollowerCountResponse> followers
) { }
