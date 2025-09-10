package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
}
