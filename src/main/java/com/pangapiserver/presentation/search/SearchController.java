package com.pangapiserver.presentation.search;

import com.pangapiserver.application.search.SearchUseCase;
import com.pangapiserver.application.search.data.TotalSearchResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.search.document.SearchDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController implements SearchDocuments {
    private final SearchUseCase useCase;

    @Override
    @GetMapping("/{keyword}")
    public DataResponse<TotalSearchResponse> search(@PathVariable("keyword") String keyword) {
        return useCase.search(keyword);
    }

    @Override
    @PostMapping("/reindex")
    public Response reindex() {
        return useCase.reindex();
    }
}
