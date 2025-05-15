package com.pangapiserver.application.user.data;

import com.pangapiserver.domain.user.enumeration.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateInfoRequest(
    String nickname,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate age,
    Gender gender,
    String profileImage,
    String bannerImage
) {
}
