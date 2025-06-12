package com.pangapiserver.presentation.payment;

import com.pangapiserver.application.payment.PaymentUseCase;
import com.pangapiserver.application.payment.data.CardDto;
import com.pangapiserver.application.payment.data.PaymentCallbackRequest;
import com.pangapiserver.application.payment.data.PaymentRequest;
import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.infrastructure.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentUseCase useCase;

    @PostMapping("/callback")
    public ResponseEntity paymentCallback(
        @ModelAttribute PaymentCallbackRequest request
    ) {
        return useCase.callback(request);
    }

    @PostMapping("/register")
    public ResponseEntity registerPayment(
        @RequestBody RegisterCardRequest request
    ) {
        System.out.println(useCase.registerCard(request));
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/test")
    public ResponseEntity testPayment(
        @RequestBody PaymentRequest request
    ){
        return useCase.paymentCardTest(request);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CardDto>>> getCards() {
        List<CardDto> CardList = useCase.getCards().stream()
            .map(CardDto::new)
            .toList();

        BaseResponse<List<CardDto>> response = BaseResponse.ok(HttpStatus.OK, CardList);
        return ResponseEntity.ok(response);
    }

}
