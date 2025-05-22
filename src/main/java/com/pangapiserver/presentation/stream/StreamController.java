package com.pangapiserver.presentation.stream;

import lombok.RequiredArgsConstructor;
import com.pangapiserver.application.stream.StreamUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pangapiserver.application.stream.data.StreamListResponse;
import java.util.List;

@RestController
@RequestMapping("/stream")
@RequiredArgsConstructor
public class StreamController {
    private final StreamUseCase useCase;

    @GetMapping("/items")
    public List<StreamListResponse> getItems() {
        return useCase.getItems();
    }

    @GetMapping("/items/popular")
    public List<StreamListResponse> getPopularItems() {
        return useCase.getPopularItems();
    }
}
