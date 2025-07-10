package com.pangapiserver.presentation.cash;

import com.pangapiserver.application.cash.CashUseCase;
import com.pangapiserver.application.cash.data.CashChargeRequest;
import com.pangapiserver.application.cash.data.CashResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.cash.document.CashDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController implements CashDocuments {
    private final CashUseCase useCase;

    @Override
    @GetMapping
    public DataResponse<CashResponse> getCash() {
        return useCase.getCash();
    }

    @Override
    @PostMapping("/payment")
    public Response chargeCash(@RequestBody CashChargeRequest request) {
        return useCase.chargeCash(request);
    }
}