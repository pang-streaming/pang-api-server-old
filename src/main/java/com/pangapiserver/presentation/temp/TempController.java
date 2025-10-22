package com.pangapiserver.presentation.temp;

import com.pangapiserver.application.temp.TempUseCase;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {
    private final TempUseCase useCase;

    @PostMapping
    public DataResponse<StartStreamResponse> createLive() {
        return useCase.createLive();
    }
}
