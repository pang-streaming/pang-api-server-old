package com.pangapiserver.application.store;

import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.store.data.CreateStoreRequest;
import com.pangapiserver.application.store.data.StoreResponse;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.store.entity.StoreUserEntity;
import com.pangapiserver.domain.store.service.StoreService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class StoreUseCase {
    private final StoreService service;
    private final UserAuthenticationHolder holder;

    public Response createStore(CreateStoreRequest request) {
        UserEntity user = holder.current();
        service.createStore(request, user);
        return Response.ok("스토어 생성 성공");
    }

    public DataResponse<List<StoreResponse>> getStores() {
        List<StoreResponse> stores = service.getAllStores().stream()
                .map(StoreResponse::of)
                .toList();
        return DataResponse.ok("스토어 목록 조회 성공", stores);
    }

    public DataResponse<List<StoreResponse>> getJoinedStores() {
        List<StoreResponse> stores = service.getJoinedStores(holder.current()).stream()
                .map(entity -> StoreResponse.of(entity.getStore()))
                .toList();
        return DataResponse.ok("가입한 스토어 목록 조회 성공", stores);
    }

    public Response joinStore(UUID storeId) {
        UserEntity user = holder.current();
        service.saveStore(storeId, user);
        return Response.ok("스토어 가입 성공");
    }

    public DataResponse<List<ProductListResponse>> getStoreProducts(UUID storeId) {
        List<ProductEntity> products = service.getProductsByStore(storeId);
        List<UUID> likedProductIds = service.findLikedProductIdsByUserId(holder.current().getId());
        Set<UUID> likedSet = new HashSet<>(likedProductIds);

        List<ProductListResponse> data = products.stream()
                .map(p -> ProductListResponse.of(p, likedSet.contains(p.getId())))
                .toList();
        return DataResponse.ok("특정 스토어의 상품 목록 조회 성공", data);
    }

    public Response leaveStore(UUID storeId) {
        if (service.deleteByStoreIdAndUser(storeId, holder.current())) {
            return Response.ok("스토어 탈퇴 성공");
        }
        return Response.ok("스토어 탈퇴 실패");
    }
}
