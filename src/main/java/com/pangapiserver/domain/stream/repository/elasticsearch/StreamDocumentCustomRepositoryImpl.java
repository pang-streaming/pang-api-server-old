package com.pangapiserver.domain.stream.repository.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScore;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScoreMode;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import com.pangapiserver.infrastructure.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class StreamDocumentCustomRepositoryImpl implements StreamDocumentCustomRepository {
    private final ElasticsearchClient client;
    private final RedisService redisService;

    @Override
    public Page<StreamResponse> searchByTitle(String keyword, List<String> chips, Pageable pageable) {
        List<FunctionScore> functions = new ArrayList<>();

        functions.add(FunctionScore.of(fn -> fn
                .randomScore(r -> r)
                .weight(0.9)
        ));

        functions.add(FunctionScore.of(fn -> fn
                .filter(f -> f.wildcard(w -> w
                        .field("title")
                        .value("*" + keyword + "*")
                        .caseInsensitive(true)
                ))
                .weight(1.2)
        ));

        if (!chips.isEmpty()) {
            for (String chip : chips) {
                functions.add(FunctionScore.of(fn -> fn
                        .filter(f -> f
                                .term(t -> t
                                        .field("chip")
                                        .value(chip)
                                        .caseInsensitive(true)
                                )
                        )
                        .weight(1.0)
                ));
            }
        }

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("streams")
                .from((int) pageable.getOffset())
                .size(pageable.getPageSize())
                .query(q -> q.functionScore(fs -> fs
                        .query(word -> word.matchAll(m -> m))
                        .functions(functions)
                        .scoreMode(FunctionScoreMode.Sum)
                ))
        );
        try {
            SearchResponse<StreamDocument> response = client.search(searchRequest, StreamDocument.class);

            List<StreamResponse> content =  response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUsername())))
                    .toList();

            long total;
            if (response.hits().total() != null) {
                total = response.hits().total().value();
            } else {
                total = content.size();
            }

            return new PageImpl<>(content, pageable, total);
        } catch (Exception e) {
            throw new ElasticsearchConnectionException();
        }
    }
}
