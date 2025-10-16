package com.pangapiserver.domain.stream.repository.elasticsearch;

import java.io.IOException;
import java.util.List;

public interface StreamDocumentCustomRepository {
    List<String> searchByTitle(String title, List<String> chips) throws IOException;
}
