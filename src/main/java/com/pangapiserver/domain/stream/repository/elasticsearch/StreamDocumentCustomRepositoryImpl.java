package com.pangapiserver.domain.stream.repository.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.pangapiserver.domain.stream.document.StreamDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StreamDocumentCustomRepositoryImpl implements StreamDocumentCustomRepository {
    private final ElasticsearchClient client;

    @Override
    public List<UUID> searchByTitle(String keyword) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("streams")
                .query(q -> q
                        .wildcard(w -> w
                                .field("title")
                                .value("*" + keyword + "*"))
                )
                .query(q -> q
                        .functionScore(fs -> fs
                                .functions(f -> f
                                        .randomScore(random -> random)
                                )
                        )
                )
                .size(20)
        );
        SearchResponse<StreamDocument> response = client.search(searchRequest, StreamDocument.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
                .map(StreamDocument::getStreamId)
                .toList();

    }
}
