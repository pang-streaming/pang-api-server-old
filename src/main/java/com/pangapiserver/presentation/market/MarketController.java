package com.pangapiserver.presentation.market;

import com.pangapiserver.application.market.MarketUseCase;
import com.pangapiserver.application.market.data.*;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.market.document.MarketDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController implements MarketDocuments {
    private final MarketUseCase useCase;

    @PostMapping("/add")
    public Response add(@RequestBody ProductAddRequest request) {
        return useCase.add(request);
    }

    @GetMapping("/items")
    public DataResponse<List<ProductListResponse>> getItems() {
        return useCase.getItems();
    }

    @GetMapping("/{productId}")
    public DataResponse<ProductDetailResponse> getItem(@PathVariable("productId") UUID productId) {
        return useCase.getItem(productId);
    }

    @PostMapping("/like")
    public Response like(@RequestBody ProductLikeRequest request) {
        return useCase.like(request);
    }

    @PostMapping("/buy")
    public DataResponse<PurchaseResponse> purchase(@RequestBody ProductBuyRequest request) {
        return useCase.purchase(request);
    }

    @PostMapping("/gift")
    public Response gift(@RequestBody ProductGiftRequest request) {
        return useCase.gift(request);
    }

    @GetMapping("/gifts")
    public DataResponse<List<PurchaseResponse>> getGifts() {
        return useCase.getGifts();
    }
}
