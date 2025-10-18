package com.pangapiserver.application.market;

import com.pangapiserver.application.market.data.*;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.enumeration.LikeStatus;
import com.pangapiserver.domain.market.exception.ProductAlreadyOwnedException;
import com.pangapiserver.domain.market.service.MarketService;
import com.pangapiserver.domain.market.service.PurchaseService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MarketUseCase {
    private final UserAuthenticationHolder holder;
    private final MarketService service;
    private final PurchaseService purchaseService;
    private final CashService cashService;
    private final UserService userService;

    public Response add(ProductAddRequest request) {
        service.saveProduct(request, holder.current().getId());
        return Response.ok("상품 추가 성공");
    }

    public DataResponse<List<ProductListResponse>> getItems() {
        UserEntity user = holder.current();
        List<ProductWithLikeStatusDto> items = service.getItemsWithLikeStatus(user);
        List<ProductListResponse> responses = items.stream()
            .map(item -> ProductListResponse.of(item.getProduct(), item.isLiked()))
            .toList();
        return DataResponse.ok("상품 목록 조회 성공", responses);
    }

    public DataResponse<ProductDetailResponse> getItem(UUID productId) {
        ProductEntity entity = service.getById(productId);
        int likes = service.getLikes(productId);
        ProductDetailResponse response = ProductDetailResponse.of(entity, likes);
        return DataResponse.ok("상품 상세 조회 성공", response);
    }

    public Response like(ProductLikeRequest request) {
        UUID productId = request.productId();
        ProductEntity product = service.getById(productId);
        UserEntity user = holder.current();
        if (service.saveLike(user, product) == LikeStatus.LIKED) {
            return Response.ok("하트 성공");
        }
        return Response.ok("언 하트 성공");
    }

    public DataResponse<PurchaseResponse> purchase(ProductBuyRequest request) {
        UserEntity user = holder.current();
        ProductEntity product = service.getById(request.productId());
        checkAlreadyOwned(user, product);
        withdrawBalance(user, product);
        purchaseService.save(user, product);
        PurchaseResponse response = PurchaseResponse.of(product);
        return DataResponse.ok("구매 성공", response);
    }

    public Response gift(ProductGiftRequest request) {
        UserEntity user = holder.current();
        UserEntity receiver = userService.getByUsername(request.username());
        ProductEntity product = service.getById(request.productId());
        checkAlreadyOwned(receiver, product);
        withdrawBalance(user, product);
        purchaseService.save(receiver, product);
        return Response.ok("선물 보내기 성공");
    }

    public DataResponse<List<PurchaseResponse>> getGifts() {
        UserEntity user = holder.current();
        List<ProductEntity> entities = purchaseService.getByUser(user);
        List<PurchaseResponse> responses = entities.stream().map(
            PurchaseResponse::of
        ).toList();
        return DataResponse.ok("선물 조회 성공", responses);
    }

    public DataResponse<List<ProductListResponse>> getTop5Products() {
        UserEntity user = holder.current();
        List<ProductEntity> items = service.getTop5LikedProducts();
        List<ProductListResponse> responses = items.stream()
            .map(item -> ProductListResponse.of(item, service.isProductLikedByUser(user, item)))
            .toList();
        return DataResponse.ok("좋아요 많은 상품 TOP 5 조회 성공", responses);
    }

    private void checkAlreadyOwned(UserEntity user, ProductEntity product) {
        if (purchaseService.existsByUserAndProduct(user, product)) {
            throw new ProductAlreadyOwnedException();
        }
    }

    private void withdrawBalance(UserEntity user, ProductEntity product) {
        String description = String.format("User %s purchased %s for %d",
            user.getId(), product.getId(), product.getPrice());
        cashService.withdraw(user, product.getPrice(), description);
    }
}
