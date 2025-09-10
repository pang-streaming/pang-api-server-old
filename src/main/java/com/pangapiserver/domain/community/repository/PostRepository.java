package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
