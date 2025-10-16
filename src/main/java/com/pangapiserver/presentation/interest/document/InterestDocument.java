package com.pangapiserver.presentation.interest.document;

import com.pangapiserver.application.interest.data.AddInterestsRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Interest Api", description = "관심사 api")
public interface InterestDocument {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "관심사 등록 API", description = "관심사를 등록합니다.")
    Response addInterests(AddInterestsRequest addInterestsRequest);
}
