package com.pangapiserver.presentation.payment;

import com.pangapiserver.application.payment.PaymentUseCase;
import com.pangapiserver.application.payment.data.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
        private final PaymentUseCase useCase;

        @PostMapping("/callback")
        public ResponseEntity paymentCallback(
                @ModelAttribute PaymentRequest request
        ) {
                return useCase.callback(request);
        }
}
