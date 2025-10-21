package com.pangapiserver.application.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.pangapiserver.application.search.data.TotalSearchResponse;
import com.pangapiserver.domain.interest.repository.InterestRepository;
import com.pangapiserver.domain.market.document.ProductDocument;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.search.SearchService;
import com.pangapiserver.domain.search.exception.DocumentReindexingException;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.document.UserDocument;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.repository.UserRepository;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
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

    private final StreamRepository streamRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ElasticsearchClient client;

    @Transactional
    public Response reindex() {
        recreateIndex("users");
        recreateIndex("streams");
        recreateIndex("products");

        userRepository.findAll().forEach(user -> {
            UserDocument doc = new UserDocument(
                    user.getId(),
                    user.getUsername(),
                    user.getNickname(),
                    user.getProfileImage(),
                    user.getBannerImage(),
                    user.getDescription(),
                    user.getRole()
            );
            indexDocument("users", user.getId().toString(), doc);
        });

        streamRepository.findAll().forEach(stream -> {
            StreamDocument doc = StreamDocument.builder()
                    .username(stream.getUser().getUsername())
                    .nickname(stream.getUser().getNickname())
                    .profileImage(stream.getUser().getProfileImage())
                    .streamId(stream.getId())
                    .streamUrl(stream.getUrl())
                    .title(stream.getTitle())
                    .chip(stream.getCategory().getChip().toString())
                    .thumbnail(stream.getThumbnail())
                    .build();
            indexDocument("streams", stream.getId().toString(), doc);
        });

        productRepository.findAll().forEach(product -> {
            ProductDocument doc = new ProductDocument(
                    product.getId(),
                    product.getImageUrl(),
                    product.getName(),
                    product.getPrice()
            );
            indexDocument("products", product.getId().toString(), doc);
        });

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
            throw new DocumentReindexingException();
        }
    }


    private <T> void indexDocument(String indexName, String id, T document) {
        try {
            client.index(i -> i
                    .index(indexName)
                    .id(id)
                    .document(document)
            );
        } catch (Exception e) {
            throw new DocumentReindexingException();
        }
    }

}
