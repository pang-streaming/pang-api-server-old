package com.pangapiserver.presentation.market;

import com.pangapiserver.application.market.MarketUseCase;
import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.market.document.MarketDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController implements MarketDocuments {
    private final MarketUseCase useCase;

    @PostMapping("/add")
    public Response add(@RequestBody ProductAddRequest request) {
        return useCase.add(request);
    }
}
