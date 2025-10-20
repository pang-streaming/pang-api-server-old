package com.pangapiserver.domain.user.repository;

import com.pangapiserver.domain.user.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, UUID>{
}
