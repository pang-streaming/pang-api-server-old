package com.pangapiserver.domain.stream.repository.elasticsearch;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface StreamDocumentCustomRepository {
    List<UUID> searchByTitle(String title) throws IOException;
}
