package com.pangapiserver.presentation.stream;

import com.pangapiserver.application.stream.StreamKeyUseCase;
import com.pangapiserver.application.stream.StreamUseCase;
import com.pangapiserver.application.stream.data.request.UpdateStreamRequest;
import com.pangapiserver.application.stream.data.response.StreamInfoResponse;
import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.stream.data.response.StreamUserResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.presentation.stream.document.StreamDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stream")
@RequiredArgsConstructor
public class StreamController implements StreamDocuments {
    private final StreamUseCase streamUseCase;
    private final StreamKeyUseCase streamKeyUseCase;

    @Override
    @GetMapping("/{streamId}")
    public DataResponse<StreamInfoResponse> getStream(@PathVariable("streamId") UUID streamId) {
        return streamUseCase.getStreamById(streamId);
    }

    @Override
    @GetMapping
    public DataResponse<List<StreamResponse>> getLiveStreams() {
        return streamUseCase.getLiveStreams();
    }

    @Override
    @GetMapping("/category/{categoryId}")
    public DataResponse<List<StreamResponse>> getStreamsByCategory(@PathVariable("categoryId") Long categoryId) {
        return streamUseCase.getStreamsByCategory(categoryId);
    }

    @Override
    @GetMapping("/following")
    public DataResponse<List<StreamResponse>> getFollowingLiveStreams() {
        return streamUseCase.getFollowingLiveStreams();
    }

    @Override
    @GetMapping("/key")
    public DataResponse<StreamKeyResponse> getKey() {
        return streamKeyUseCase.getKey();
    }

    @Override
    @PostMapping("/key")
    public DataResponse<StreamKeyResponse> createKey() {
        return streamKeyUseCase.createKey();
    }

    @Override
    @PostMapping
    public DataResponse<StreamUserResponse> createStreamByKey(@RequestHeader("X-Stream-Key") String key) {
        return streamUseCase.createStreamByKey(key);
    }

    @Override
    @PatchMapping("/{streamId}")
    public DataResponse<StreamInfoResponse> updateStream(@PathVariable("streamId") UUID streamId, @RequestBody UpdateStreamRequest request) {
        return streamUseCase.updateStream(streamId, request);
    }
}
