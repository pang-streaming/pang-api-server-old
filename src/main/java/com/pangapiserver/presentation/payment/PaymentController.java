package com.pangapiserver.presentation.payment;

import com.pangapiserver.application.payment.PaymentUseCase;
import com.pangapiserver.application.payment.data.PaymentRequest;
import com.pangapiserver.application.payment.data.RegisterCardRequest;
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

        @PostMapping("/register")
        public ResponseEntity registerPayment(
                @RequestBody RegisterCardRequest request
        ) {
                System.out.println(useCase.registerCard(request));
                return ResponseEntity.ok("SUCCESS");
        }

}
