package com.pangapiserver.presentation.interest;

import com.pangapiserver.application.interest.InterestUseCase;
import com.pangapiserver.application.interest.data.AddInterestsRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.interest.document.InterestDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interests")
public class InterestController implements InterestDocument {
    private final InterestUseCase interestUseCase;

    @Override
    @PostMapping
    public Response addInterests(@RequestBody AddInterestsRequest addInterestsRequest) {
        return interestUseCase.addInterests(addInterestsRequest);
    }
}
