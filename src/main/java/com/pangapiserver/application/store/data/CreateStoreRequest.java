package com.pangapiserver.application.store.data;

public record CreateStoreRequest(
        String name,
        String description,
        String profileImage,
        String bannerImage
) {
}
