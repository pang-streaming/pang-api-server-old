package com.pangapiserver.presentation.cash;

import com.pangapiserver.domain.cash.data.CashResponse;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {
    private final CashService cashService;

    @GetMapping
    public DataResponse<CashResponse> getCash() {
        return cashService.getCash();
    }
}
