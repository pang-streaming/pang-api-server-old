package com.pangapiserver.presentation.temp;

import com.pangapiserver.application.temp.TempUseCase;
import com.pangapiserver.application.temp.data.request.UpdateTempStreamRequest;
import com.pangapiserver.domain.stream.entity.TempStreamEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
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

    @PatchMapping("/{uid}")
    public Response updateTempStream(@PathVariable String uid, @RequestBody UpdateTempStreamRequest request) {
        return useCase.updateTempStream(uid, request);
    }
}
