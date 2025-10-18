package com.pangapiserver.application.market;

import com.pangapiserver.application.market.data.*;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.enumeration.LikeStatus;
import com.pangapiserver.domain.market.enumeration.ProductCategory;
import com.pangapiserver.domain.market.exception.ProductAlreadyOwnedException;
import com.pangapiserver.domain.market.service.MarketService;
import com.pangapiserver.domain.market.service.PurchaseService;
import com.pangapiserver.domain.store.service.StoreService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MarketUseCase {
    private final UserAuthenticationHolder holder;
    private final MarketService service;
    private final PurchaseService purchaseService;
    private final CashService cashService;
    private final UserService userService;
    private final StoreService storeService;

    public Response add(ProductAddRequest request) {
        service.saveProduct(request, holder.current().getId());
        return Response.ok("상품 추가 성공");
    }

    public DataResponse<Page<ProductListResponse>> getItems(Pageable pageable) {
        UserEntity user = holder.current();
        Page<ProductWithLikeStatusDto> items = service.getItemsWithLikeStatus(user, pageable);
        List<ProductListResponse> responses = items.stream()
            .map(item -> ProductListResponse.of(item.getProduct(), item.isLiked()))
            .toList();
        Page<ProductListResponse> data = new PageImpl<>(responses);
        return DataResponse.ok("상품 목록 조회 성공", data);
    }

    public DataResponse<ProductDetailResponse> getItem(UUID productId) {
        System.out.println("MarketUseCase#######################productId: " + productId);
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
        purchaseService.save(user, product, request.address(), user.getEmail());
        PurchaseResponse response = PurchaseResponse.of(product);
        return DataResponse.ok("구매 성공", response);
    }

    public Response gift(ProductGiftRequest request) {
        UserEntity user = holder.current();
        UserEntity receiver = userService.getByUsername(request.username());
        ProductEntity product = service.getById(request.productId());
        checkAlreadyOwned(receiver, product);
        withdrawBalance(user, product);
        // buyer는 결제한 사람(user), receiver는 받은 사람
        com.pangapiserver.domain.market.entity.PurchaseEntity purchase = com.pangapiserver.domain.market.entity.PurchaseEntity.builder()
            .product(product)
            .buyer(user)
            .receiver(receiver)
            .address(null)
            .email(receiver.getEmail())
            .deliveryStatus(com.pangapiserver.domain.market.enumeration.DeliveryStatus.PREPARING)
            .createdAt(java.time.LocalDateTime.now())
            .build();
        purchaseService.updatePurchase(purchase);
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

    public DataResponse<List<PurchaseDetailResponse>> getPurchases() {
        UserEntity user = holder.current();
        List<com.pangapiserver.domain.market.entity.PurchaseEntity> purchases = purchaseService.getPurchasesByUser(user);
        List<PurchaseDetailResponse> responses = purchases.stream()
            .map(PurchaseDetailResponse::of)
            .toList();
        return DataResponse.ok("구매 목록 조회 성공", responses);
    }

    public DataResponse<List<ReceivedGiftResponse>> getReceivedGifts() {
        UserEntity user = holder.current();
        List<com.pangapiserver.domain.market.entity.PurchaseEntity> gifts = purchaseService.getReceivedGiftsByUser(user);
        List<ReceivedGiftResponse> responses = gifts.stream()
            .map(ReceivedGiftResponse::of)
            .toList();
        return DataResponse.ok("받은 선물 조회 성공", responses);
    }

    public Response updateGift(GiftUpdateRequest request) {
        UserEntity user = holder.current();
        com.pangapiserver.domain.market.entity.PurchaseEntity purchase = purchaseService.getById(request.purchaseId());
        
        // 권한 확인: 받은 선물만 수정 가능
        if (purchase.getReceiver() == null || !purchase.getReceiver().getId().equals(user.getId())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        
        // 주소와 배송 상태 업데이트
        if (request.address() != null) {
            purchase = com.pangapiserver.domain.market.entity.PurchaseEntity.builder()
                .id(purchase.getId())
                .product(purchase.getProduct())
                .buyer(purchase.getBuyer())
                .receiver(purchase.getReceiver())
                .address(request.address())
                .email(purchase.getEmail())
                .deliveryStatus(request.deliveryStatus() != null ? request.deliveryStatus() : purchase.getDeliveryStatus())
                .createdAt(purchase.getCreatedAt())
                .build();
        } else if (request.deliveryStatus() != null) {
            purchase = com.pangapiserver.domain.market.entity.PurchaseEntity.builder()
                .id(purchase.getId())
                .product(purchase.getProduct())
                .buyer(purchase.getBuyer())
                .receiver(purchase.getReceiver())
                .address(purchase.getAddress())
                .email(purchase.getEmail())
                .deliveryStatus(request.deliveryStatus())
                .createdAt(purchase.getCreatedAt())
                .build();
        }
        
        purchaseService.updatePurchase(purchase);
        return Response.ok("선물 정보 수정 성공");
    }

    public DataResponse<List<ProductListResponse>> getTop5Products() {
        UserEntity user = holder.current();
        List<ProductEntity> items = service.getTop5LikedProducts();
        List<ProductListResponse> responses = items.stream()
            .map(item -> ProductListResponse.of(item, service.isProductLikedByUser(user, item)))
            .toList();
        return DataResponse.ok("좋아요 많은 상품 TOP 5 조회 성공", responses);
    }

    public DataResponse<List<ProductListResponse>> getItemsByCategory(ProductCategory category) {
        List<ProductEntity> products = service.getItemsByCategory(category);
        List<UUID> likedProductIds = storeService.findLikedProductIdsByUserId(holder.current().getId());
        Set<UUID> likedSet = new HashSet<>(likedProductIds);

        List<ProductListResponse> data = products.stream()
                .map(p -> ProductListResponse.of(p, likedSet.contains(p.getId())))
                .toList();

        return DataResponse.ok("카테고리 별 상품 조회 성공", data);
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
