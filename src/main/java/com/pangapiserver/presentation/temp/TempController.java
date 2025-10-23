package com.pangapiserver.presentation.temp;

import com.pangapiserver.application.temp.TempUseCase;
import com.pangapiserver.application.temp.data.CreateLiveResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {
    private final TempUseCase useCase;

    @PostMapping
    public DataResponse<CreateLiveResponse> createLive() {
        return useCase.createLive();
    }
//
//    @PostMapping("/{uid}")
//    public DataResponse<> createStream(@PathVariable String uid) {
//        return useCase.createStream();
//    }
}
