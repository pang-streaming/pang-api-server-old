package com.pangapiserver.domain.search.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScore;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScoreMode;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.pangapiserver.application.market.data.ProductListResponse;
import com.pangapiserver.application.search.data.TotalSearchData;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.user.data.UserListResponse;
import com.pangapiserver.domain.market.document.ProductDocument;
import com.pangapiserver.domain.market.exception.ProductAlreadyOwnedException;
import com.pangapiserver.domain.market.repository.ProductLikeRepository;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.search.exception.ElasticsearchProductsSearchException;
import com.pangapiserver.domain.search.exception.ElasticsearchStreamsSearchException;
import com.pangapiserver.domain.search.exception.ElasticsearchUsersSearchException;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.user.document.UserDocument;
import com.pangapiserver.infrastructure.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class SearchCustomRepositoryImpl implements SearchCustomRepository {
    private final ElasticsearchClient client;
    private final RedisService redisService;
    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;

    @Override
    public List<StreamResponse> searchStreams(String keyword, TotalSearchData data) {
        try {
            SearchRequest request = buildSearchRequest(keyword, data);
            SearchResponse<StreamDocument> response = client.search(request, StreamDocument.class);

            return response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUsername())))
                    .toList();
        } catch (Exception e) {
            throw new ElasticsearchStreamsSearchException();
        }
    }

    @Override
    public List<UserListResponse> searchUsers(String keyword, TotalSearchData data) {
        try {
            SearchRequest request = buildSearchRequest(keyword, data);
            SearchResponse<UserDocument> response = client.search(request, UserDocument.class);

            List<UserDocument> userDocs = response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .filter(f -> !f.getUsername().equals(data.user().getUsername()))
                    .toList();

            return UserListResponse.ofDocument(userDocs, data.followers());
        } catch (Exception e) {
            throw new ElasticsearchUsersSearchException();
        }
    }

    @Override
    public List<ProductListResponse> searchProducts(String keyword, TotalSearchData data) {
        try {
            SearchRequest request = buildSearchRequest(keyword, data);
            SearchResponse<ProductDocument> response = client.search(request, ProductDocument.class);

            return response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(s -> ProductListResponse.of(
                            s,
                            productLikeRepository.existsByUserAndProduct(
                                    data.user(),
                                    productRepository.findById(s.getId()).orElseThrow(ProductAlreadyOwnedException::new)
                            )
                    ))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private SearchRequest buildSearchRequest(String keyword, TotalSearchData data) {
        List<FunctionScore> functions = new ArrayList<>();

        functions.add(FunctionScore.of(fn -> fn.randomScore(r -> r).weight(0.9)));

        functions.add(FunctionScore.of(fn -> fn
                .filter(f -> f.wildcard(w -> w
                        .field(data.field())
                        .value("*" + keyword + "*")
                        .caseInsensitive(true)
                ))
                .weight(1.2)
        ));

        if (data.chips() != null && !data.chips().isEmpty()) {
            for (String chip : data.chips()) {
                functions.add(FunctionScore.of(fn -> fn
                        .filter(f -> f.term(t -> t
                                .field("chip")
                                .value(chip)
                                .caseInsensitive(true)
                        ))
                        .weight(1.0)
                ));
            }
        }

        return SearchRequest.of(s -> s
                .index(data.index())
                .query(q -> q.functionScore(fs -> fs
                        .functions(functions)
                        .scoreMode(FunctionScoreMode.Sum)
                ))
        );
    }
}
