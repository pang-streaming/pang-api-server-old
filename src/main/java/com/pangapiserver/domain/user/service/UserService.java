package com.pangapiserver.domain.user.service;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserAleadyExistException;
import com.pangapiserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void create(UserEntity user) {
        repository.save(user);
    }

    public void validateByUsernameAndEmail(String username, String email) {
        if (getByUsername(username) != null || getByEmail(email) != null) throw new UserAleadyExistException();
    }

    public UserEntity getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
