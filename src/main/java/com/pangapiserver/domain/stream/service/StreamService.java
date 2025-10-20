package com.pangapiserver.domain.stream.service;

import com.pangapiserver.application.stream.data.request.UpdateStreamRequest;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.category.exception.CategoryNotFoundException;
import com.pangapiserver.domain.category.repository.CategoryRepository;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.domain.stream.document.StreamDocument;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.stream.exception.StreamAlreadyEndedException;
import com.pangapiserver.domain.stream.exception.StreamNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.stream.repository.elasticsearch.StreamDocumentRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.WatchHistoryEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;
    private final WatchHistoryRepository watchHistoryRepository;
    private final CategoryRepository categoryRepository;
    private final StreamDocumentRepository streamDocumentRepository;
    private final FollowRepository followRepository;

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

    public StreamEntity getById(UUID streamId) {
        return repository.findById(streamId)
                .orElseThrow(StreamNotFoundException::new);
    }

    public List<StreamEntity> getLiveStreams() {
        return repository.findAllByStatus(StreamStatus.LIVE);
    }

    public List<StreamEntity> getStreamsByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return repository.findAllByCategory(category);
    }

    public StreamEntity getLiveStreamByUser(UserEntity user) {
        return repository.findByUserAndStatus(user, StreamStatus.LIVE)
                .orElseThrow(StreamNotFoundException::new);
    }

    public void closeStream(StreamEntity stream) {
        if (stream.getStatus() == StreamStatus.ENDED)
            throw new StreamAlreadyEndedException();
        stream.updateEndAt();
        repository.save(stream);
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

    public void updateStream(UUID streamId, UserEntity user, String title, Long categoryId, List<String> tags, String thumbnail) {
        StreamEntity stream = repository.findById(streamId).orElseThrow(StreamNotFoundException::new);
        if (!stream.getUser().equals(user)) {
            throw new StreamNotFoundException();
        }
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        stream.updateStream(category, title, tags, thumbnail);
        repository.save(stream);
    }

    public void saveDocument(StreamEntity stream) {
        StreamDocument document = StreamDocument.builder()
                .username(stream.getUser().getUsername())
                .nickname(stream.getUser().getNickname())
                .profileImage(stream.getUser().getProfileImage())
                .streamId(stream.getId())
                .streamUrl(stream.getUrl())
                .title(stream.getTitle())
                .thumbnail(stream.getThumbnail())
                .chip(stream.getCategory().getChip().toString())
                .build();
        streamDocumentRepository.save(document);
    }

    public Page<StreamResponse> searchByTitle(String keyword, List<String> chips, Pageable pageable) {
        return streamDocumentRepository.searchByTitle(keyword, chips, pageable);
    }

    public List<StreamEntity> getEndedStreams() {
        return repository.findAllByStatusOrderByIdDesc(StreamStatus.ENDED);
    }

    public List<StreamEntity> getEndedStreamsOfFollowings(UserEntity user) {
        List<FollowEntity> followings = followRepository.findFollowerByUser(user);
        List<UserEntity> followedUsers = followings.stream()
                .map(FollowEntity::getUser)
                .collect(Collectors.toList());
        return repository.findAllByUserInAndStatusOrderByIdDesc(followedUsers, StreamStatus.ENDED);
    }
}
