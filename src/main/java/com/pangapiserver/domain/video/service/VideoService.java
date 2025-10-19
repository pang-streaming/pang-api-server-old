package com.pangapiserver.domain.video.service;

import com.pangapiserver.domain.category.entity.CategoryEntity;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamStatus;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final WatchHistoryRepository watchHistoryRepository;
    private final StreamRepository streamRepository;

    public List<StreamEntity> getRecent(UserEntity user) {
        return watchHistoryRepository.getRecent(user);
    }

    public List<StreamEntity> getEndedVideosByCategory(CategoryEntity category) {
        return streamRepository.findAllByCategoryAndStatus(category, StreamStatus.ENDED);
    }
}
