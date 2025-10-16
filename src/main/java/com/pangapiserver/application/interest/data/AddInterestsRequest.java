package com.pangapiserver.application.interest.data;

import com.pangapiserver.domain.category.enumeration.Chip;

import java.util.List;

public record AddInterestsRequest(
        List<Chip> interests
) {
}
