package com.pangapiserver.presentation.user;

import com.pangapiserver.application.user.UserUseCase;
import com.pangapiserver.application.user.data.UpdateInfoRequest;
import com.pangapiserver.application.user.data.UserInfoResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCase useCase;

    @GetMapping("/me")
    public DataResponse<UserInfoResponse> getMyInfo() {
        return useCase.getMyInfo();
    }

    @PatchMapping
    public void updateUser(UpdateInfoRequest request) {
        useCase.updateInfo(request);
    }
}
