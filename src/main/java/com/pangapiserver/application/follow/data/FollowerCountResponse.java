package com.pangapiserver.application.follow.data;

import java.util.UUID;

public record FollowerCountResponse (UUID id, Long count) {}