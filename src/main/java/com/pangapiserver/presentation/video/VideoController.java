package com.pangapiserver.presentation.video;

import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.video.VideoUseCase;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.presentation.video.document.VideoDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController implements VideoDocuments {
    private final VideoUseCase useCase;

    @Override
    @GetMapping("/recent")
    public DataResponse<List<StreamResponse>> getRecent() {
        return useCase.getRecent();
    }

    @Override
    @GetMapping("/streamer")
    public DataResponse<StreamResponse> getLiveStreamByUsername(@RequestParam(name = "username") String username) {
        return useCase.getLiveVideoByUsername(username);
    }

    @Override
    @GetMapping("/streamer/recorded")
    public DataResponse<List<StreamResponse>> getVideoByUsername(@RequestParam(name = "username") String username) {
        return useCase.getRecordedVideoByUsername(username);
    }

    @Override
    @GetMapping("/category/{categoryId}")
    public DataResponse<List<StreamResponse>> getEndedVideosByCategory(@PathVariable("categoryId") Long categoryId) {
        return useCase.getEndedVideosByCategory(categoryId);
    }

    @Override
    @GetMapping()
    public DataResponse<List<StreamResponse>> getEndedStreams() {
        return useCase.getEndedStreams();
    }
}