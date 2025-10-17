package com.pangapiserver.domain.stream.repository.elasticsearch;

import com.pangapiserver.application.stream.data.response.StreamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StreamDocumentCustomRepository {
    Page<StreamResponse> searchByTitle(String title, List<String> chips, Pageable pageable);
}
