package com.pangapiserver.domain.stream.repository.elasticsearch;

import com.pangapiserver.application.stream.data.response.StreamResponse;

import java.util.List;

public interface StreamDocumentCustomRepository {
    List<StreamResponse> searchByTitle(String title, List<String> chips);
}
