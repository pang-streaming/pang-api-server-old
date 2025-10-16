package com.pangapiserver.domain.stream.repository.elasticsearch;

import com.pangapiserver.domain.stream.document.StreamDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StreamDocumentRepository extends ElasticsearchRepository<StreamDocument, UUID>, StreamDocumentCustomRepository {

}
