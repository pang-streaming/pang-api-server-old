package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, UUID> {
}
