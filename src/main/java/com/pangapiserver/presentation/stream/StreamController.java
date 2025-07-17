package com.pangapiserver.presentation.stream;

import com.pangapiserver.application.stream.StreamKeyUseCase;
import com.pangapiserver.application.stream.StreamUseCase;
import com.pangapiserver.application.stream.data.StreamListResponse;
import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.presentation.stream.document.StreamDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stream")
@RequiredArgsConstructor
public class StreamController implements StreamDocuments {
    private final StreamUseCase streamUseCase;
    private final StreamKeyUseCase streamKeyUseCase;

    @Override
    @GetMapping
    public List<StreamListResponse> getStreams() {
        return streamUseCase.getStreams();
    }

    @Override
    @GetMapping("/items/popular")
    public List<StreamListResponse> getPopularItems() {
        return streamUseCase.getPopularItems();
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
}
