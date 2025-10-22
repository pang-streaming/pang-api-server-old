package com.pangapiserver.presentation.temp;

import com.pangapiserver.application.temp.TempUseCase;
import com.pangapiserver.domain.stream.entity.TempStreamEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {
    private final TempUseCase useCase;

    @PostMapping
    public DataResponse<StartStreamResponse> createLive() {
        return useCase.createLive();
    }

    @GetMapping("/{uid}")
    public DataResponse<TempStreamEntity> getTempStreamByUid(@PathVariable String uid) {
        return useCase.getTempStreamByUid(uid);
    }
}
