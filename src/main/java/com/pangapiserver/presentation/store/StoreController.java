package com.pangapiserver.presentation.store;

import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.store.StoreUseCase;
import com.pangapiserver.application.store.data.CreateStoreRequest;
import com.pangapiserver.application.store.data.StoreResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.store.document.StoreDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController implements StoreDocuments {
    private final StoreUseCase useCase;

    @Override
    @PostMapping
    public Response createStore(@RequestBody CreateStoreRequest request) {
        return useCase.createStore(request);
    }

    @Override
    @GetMapping
    public DataResponse<List<StoreResponse>> getStores() {
        return useCase.getStores();
    }

    @Override
    @GetMapping("/joined")
    public DataResponse<List<StoreResponse>> getJoinedStores() {
        return useCase.getJoinedStores();
    }

    @Override
    @PostMapping("/{storeId}")
    public Response joinStore(@PathVariable("storeId") UUID storeId) {
        return useCase.joinStore(storeId);
    }

    @Override
    @GetMapping("/{storeId}")
    public DataResponse<List<ProductListResponse>> getStoreProducts(@PathVariable("storeId") UUID storeId) {
        return useCase.getStoreProducts(storeId);
    }

    @Override
    @DeleteMapping("/leave/{storeId}")
    public Response leaveStore(@PathVariable("storeId") UUID storeId) {
        return useCase.leaveStore(storeId);
    }
}
