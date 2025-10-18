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

    @Override
    @PostMapping("/add")
    public Response add(@RequestBody ProductAddRequest request) {
        return useCase.add(request);
    }

    @Override
    @GetMapping("/items")
    public DataResponse<List<ProductListResponse>> getItems() {
        return useCase.getItems();
    }

    @Override
    @GetMapping("/{productId}")
    public DataResponse<ProductDetailResponse> getItem(@PathVariable("productId") UUID productId) {
        return useCase.getItem(productId);
    }

    @Override
    @PostMapping("/like")
    public Response like(@RequestBody ProductLikeRequest request) {
        return useCase.like(request);
    }

    @Override
    @PostMapping("/buy")
    public DataResponse<PurchaseResponse> purchase(@RequestBody ProductBuyRequest request) {
        return useCase.purchase(request);
    }

    @Override
    @PostMapping("/gift")
    public Response gift(@RequestBody ProductGiftRequest request) {
        return useCase.gift(request);
    }

    @Override
    @GetMapping("/gifts")
    public DataResponse<List<PurchaseResponse>> getGifts() {
        return useCase.getGifts();
    }

    @Override
    @GetMapping("/purchases")
    public DataResponse<List<PurchaseDetailResponse>> getPurchases() {
        return useCase.getPurchases();
    }

    @Override
    @GetMapping("/received-gifts")
    public DataResponse<List<ReceivedGiftResponse>> getReceivedGifts() {
        return useCase.getReceivedGifts();
    }

    @Override
    @PutMapping("/gift")
    public Response updateGift(@RequestBody GiftUpdateRequest request) {
        return useCase.updateGift(request);
    }

    @Override
    @GetMapping("/top5")
    public DataResponse<List<ProductListResponse>> getTop5Products() {
        return useCase.getTop5Products();
    }
}
