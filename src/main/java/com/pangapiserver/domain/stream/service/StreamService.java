package com.pangapiserver.domain.stream.service;

import com.pangapiserver.application.stream.data.request.UpdateStreamRequest;
import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.exception.CategoryNotFoundException;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.exception.StreamNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.stream.repository.elasticsearch.StreamDocumentRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.WatchHistoryEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final CategoryRepository categoryRepository;
    private final StreamDocumentRepository streamDocumentRepository;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    public StreamEntity getByStreamId(UUID streamId, UserEntity user) {
        StreamEntity stream = repository.findById(streamId)
                .orElseThrow(StreamNotFoundException::new);
        if (stream.getEndAt() == null) {
            return stream;
        }
        WatchHistoryEntity entity = WatchHistoryEntity.builder()
                .user(user)
                .stream(stream)
                .build();
        if (watchHistoryRepository.existsByStreamAndUser(stream, user)) {
            watchHistoryRepository.deleteByStreamAndUser(stream, user);
        }

        watchHistoryRepository.save(entity);
        return stream;
    }

    public List<StreamEntity> getLiveStreams() {
        return repository.findByEndAtIsNull();
    }

    public List<StreamEntity> getStreamsByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return repository.findAllByCategory(category);
    }

    public StreamEntity getLiveStreamByUser(UserEntity user) {
        return repository.findByUserAndEndAtNull(user)
                .orElseThrow(StreamNotFoundException::new);
    }

    public List<StreamEntity> getRecordedStreamByUser(UserEntity user) {
        return repository.findByEndAtIsNotNullAndUser(user);
    }

    public void save(StreamEntity stream) {
        repository.save(stream);
    }

    public void update(StreamEntity stream) {
        stream.updateEndAt();
        repository.save(stream);
    }

    public void updateStream(UUID streamId, UserEntity user, UpdateStreamRequest request) {
        StreamEntity stream = repository.findById(streamId).orElseThrow(StreamNotFoundException::new);
        if (!stream.getUser().equals(user)) {
            throw new StreamNotFoundException();
        }
        CategoryEntity category = categoryRepository.findById(request.categoryId()).orElseThrow(CategoryNotFoundException::new);
        stream.updateStream(category, request.title(), request.tags());
        repository.save(stream);
    }

    public void saveDocument(StreamEntity stream) {
        StreamDocument document = StreamDocument.builder()
                .username(stream.getUser().getUsername())
                .profileImage(stream.getUser().getProfileImage())
                .streamId(stream.getId())
                .title(stream.getTitle())
                .chip(stream.getCategory().getChip().toString())
                .build();
        streamDocumentRepository.save(document);
    }

    public List<String> searchByTitle(String keyword, List<String> chips) {
        try {
            return streamDocumentRepository.searchByTitle(keyword, chips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
