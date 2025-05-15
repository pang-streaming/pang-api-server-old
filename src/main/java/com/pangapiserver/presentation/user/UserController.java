package com.pangapiserver.presentation.user;

import com.pangapiserver.application.user.UserUseCase;
import com.pangapiserver.application.user.data.UpdateInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase useCase;

    @PatchMapping
    public void updateUser(UpdateInfoRequest request) {
        useCase.updateInfo(request);
    }
}
