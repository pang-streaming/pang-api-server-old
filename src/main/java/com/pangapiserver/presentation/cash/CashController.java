package com.pangapiserver.presentation.cash;

import com.pangapiserver.application.cash.CashUseCase;
import com.pangapiserver.application.cash.data.CashResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {
    private final CashUseCase useCase;

    @GetMapping
    public DataResponse<CashResponse> getCash() {
        return useCase.getCash();
    }

    @PostMapping
    public Response chargeCash() {
        return useCase.chargeCash();
    }
}
