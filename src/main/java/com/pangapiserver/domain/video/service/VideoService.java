package com.pangapiserver.domain.video.service;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final WatchHistoryRepository repository;

    public List<StreamEntity> getRecent(UserEntity user) {
        return repository.getRecent(user);
    }
}
