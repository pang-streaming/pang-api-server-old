package com.pangapiserver.domain.stream.repository.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScore;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScoreMode;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.infrastructure.elasticsearch.exception.ElasticsearchConnectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class StreamDocumentCustomRepositoryImpl implements StreamDocumentCustomRepository {
    private final ElasticsearchClient client;

    @Override
    public List<String> searchByTitle(String keyword, List<String> chips) {
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
                .query(q -> q.functionScore(fs -> fs
                        .query(word -> word.matchAll(m -> m))
                        .functions(functions)
                        .scoreMode(FunctionScoreMode.Sum)
                ))
                .size(20)
        );
        try {
            SearchResponse<StreamDocument> response = client.search(searchRequest, StreamDocument.class);

            return response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(StreamDocument::getTitle)
                    .toList();
        } catch (Exception e) {
            throw new ElasticsearchConnectionException();
        }
    }
}
