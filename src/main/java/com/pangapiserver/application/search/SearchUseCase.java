package com.pangapiserver.application.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import com.pangapiserver.application.search.data.TotalSearchResponse;
import com.pangapiserver.domain.interest.repository.InterestRepository;
import com.pangapiserver.domain.market.document.ProductDocument;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.search.SearchService;
import com.pangapiserver.domain.search.exception.DocumentReindexingException;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.document.UserDocument;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.repository.UserRepository;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchUseCase {
    private final SearchService service;
    private final UserAuthenticationHolder holder;
    private final InterestRepository interestRepository;

    public DataResponse<TotalSearchResponse> search(String keyword) {
        UserEntity user = holder.current();
        List<String> chips = interestRepository.getChipsWithUser(user);
        TotalSearchResponse response = service.searchByKeyword(keyword, chips, holder.current());
        return DataResponse.ok("통합 검색 결과 조회 성공", response);
    }

    private final UserRepository userRepository;
    private final StreamRepository streamRepository;
    private final ProductRepository productRepository;
    private final ElasticsearchClient client;

    private static final int BATCH_SIZE = 100;

    @Transactional(readOnly = true)
    public Response reindex() {
        recreateIndex("users");
        recreateIndex("streams");
        recreateIndex("products");

        reindexUsers();
        reindexStreams();
        reindexProducts();

        return Response.ok("모든 인덱스 재생성 및 리인덱싱 성공");
    }

    private void recreateIndex(String indexName) {
        try {
            boolean exists = client.indices().exists(e -> e.index(indexName)).value();
            if (exists) {
                client.indices().delete(d -> d.index(indexName));
            }
            client.indices().create(c -> c.index(indexName));
        } catch (Exception e) {
            throw new RuntimeException("Failed to recreate index: " + indexName, e);
        }
    }

    private void reindexUsers() {
        int page = 0;
        List<UserEntity> users;
        do {
            users = userRepository.findAll(PageRequest.of(page, BATCH_SIZE)).getContent();
            bulkIndexUsers(users);
            page++;
        } while (!users.isEmpty());
    }

    private void bulkIndexUsers(List<UserEntity> users) {
        if (users.isEmpty()) return;

        BulkRequest.Builder bulkBuilder = new BulkRequest.Builder();
        for (UserEntity user : users) {
            UserDocument doc = new UserDocument(
                    user.getId(),
                    user.getUsername(),
                    user.getNickname(),
                    user.getProfileImage(),
                    user.getBannerImage(),
                    user.getDescription(),
                    user.getRole()
            );
            bulkBuilder.operations(op -> op
                    .index(idx -> idx.index("users").id(user.getId().toString()).document(doc))
            );
        }

        try {
            client.bulk(bulkBuilder.build());
        } catch (Exception e) {
            throw new RuntimeException("Failed to bulk index users", e);
        }
    }

    private void reindexStreams() {
        int page = 0;
        List<StreamEntity> streams;
        do {
            streams = streamRepository.findAll(PageRequest.of(page, BATCH_SIZE)).getContent();
            bulkIndexStreams(streams);
            page++;
        } while (!streams.isEmpty());
    }

    private void bulkIndexStreams(List<StreamEntity> streams) {
        if (streams.isEmpty()) return;

        BulkRequest.Builder bulkBuilder = new BulkRequest.Builder();
        for (StreamEntity stream : streams) {
            StreamDocument doc = StreamDocument.builder()
                    .username(stream.getUser().getUsername())
                    .nickname(stream.getUser().getNickname())
                    .profileImage(stream.getUser().getProfileImage())
                    .streamId(stream.getId())
                    .streamUrl(stream.getUrl())
                    .title(stream.getTitle())
                    .chip(stream.getCategory() != null ? stream.getCategory().getChip().toString() : "NONE")
                    .thumbnail(stream.getThumbnail())
                    .build();
            bulkBuilder.operations(op -> op
                    .index(idx -> idx.index("streams").id(stream.getId().toString()).document(doc))
            );
        }

        try {
            client.bulk(bulkBuilder.build());
        } catch (Exception e) {
            throw new RuntimeException("Failed to bulk index streams", e);
        }
    }

    private void reindexProducts() {
        int page = 0;
        List<ProductEntity> products;
        do {
            products = productRepository.findAll(PageRequest.of(page, BATCH_SIZE)).getContent();
            bulkIndexProducts(products);
            page++;
        } while (!products.isEmpty());
    }

    private void bulkIndexProducts(List<ProductEntity> products) {
        if (products.isEmpty()) return;

        BulkRequest.Builder bulkBuilder = new BulkRequest.Builder();
        for (ProductEntity product : products) {
            ProductDocument doc = new ProductDocument(
                    product.getId(),
                    product.getImageUrl(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );
            bulkBuilder.operations(op -> op
                    .index(idx -> idx.index("products").id(product.getId().toString()).document(doc))
            );
        }

        try {
            client.bulk(bulkBuilder.build());
        } catch (Exception e) {
            throw new RuntimeException("Failed to bulk index products", e);
        }
    }

}
