package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.document.ProductDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDocumentRepository extends JpaRepository<ProductDocument, UUID> {
}
