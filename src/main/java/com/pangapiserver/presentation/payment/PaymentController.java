package com.pangapiserver.presentation.payment;

import com.pangapiserver.application.payment.PaymentUseCase;
import com.pangapiserver.application.payment.data.CardDto;
import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.payment.document.PaymentDocuments;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController implements PaymentDocuments {
    private final PaymentUseCase useCase;

    @PostMapping("/card")
    public Response registerPayment(
        @RequestBody RegisterCardRequest request
    ) {
        return useCase.registerCard(request);
    }

    @GetMapping("/card")
    public DataResponse<List<CardDto>> getCards() {
        List<CardDto> CardList = useCase.getCards().stream()
            .map(card -> new CardDto(
                card.getCardId(),
                card.getProvider(),
                card.getName()
            ))
            .toList();

        return DataResponse.ok("카드 목록을 성공적으로 불러왔습니다.", CardList);
    }

}
