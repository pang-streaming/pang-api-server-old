package com.pangapiserver.application.payment;

import com.pangapiserver.application.payment.data.PaymentCallbackRequest;
import com.pangapiserver.application.payment.data.PaymentRequest;
import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.card.repository.CardRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.payment.dto.PaymentCardResponse;
import com.pangapiserver.infrastructure.payment.dto.RegisterCardResponse;
import com.pangapiserver.infrastructure.payment.properties.PaymentProperties;
import com.pangapiserver.infrastructure.payment.service.PayappService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {
    private final PaymentProperties properties;
    private final PayappService payappService;
    private final UserAuthenticationHolder holder;
    private final CardRepository cardRepository;
    private Boolean isMatchingCredentials(String linkkey, String linkval) {
        return properties.getLinkkey().equals(linkkey) &&
            properties.getLinkval().equals(linkval);
    }

    // 카드등록
    public boolean registerCard(RegisterCardRequest request) {
        UserEntity user = holder.current();

        RegisterCardResponse result = payappService.registerCard(
            user.getEmail(),
            request.cardNumber(),
            request.expiredYear(),
            request.expiredMonth(),
            request.birth(),
            request.cardPassword(),
            request.phone(),
            request.name()
        );

        if (result.getState().equals("1")) {
            cardRepository.save(
                CardEntity.builder()
                    .encryption_key(result.getEncBill())
                    .number(request.phone())
                    .provider(result.getCardname())
                    .user(user)
                    .name(result.getCardno())
                    .phone(request.phone())
                    .build()
            );
        }
        return true;
    }

    public List<CardEntity> getCards() {
        UserEntity user = holder.current();
        return cardRepository.findAllByUser(user);
    }

    public ResponseEntity paymentCardTest(PaymentRequest request) {
        CardEntity card = cardRepository.findByCardId(request.card_id());
        System.out.println(card.getCardId());
        System.out.println(card.getEncryption_key());
        PaymentCardResponse res = payappService.paymentCard(
                card.getEncryption_key(),
                request.name(),
                request.price().toString(),
                card.getPhone()
            );
        if (res.getState().equals("1")) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(res.getErrorMessage());
        }
    }

    public ResponseEntity callback(PaymentCallbackRequest paymentResponse) {
        // 변조된 클라이언트 처리
        if (!isMatchingCredentials(properties.getLinkkey(), paymentResponse.linkval()))
                return ResponseEntity.badRequest().build();

        switch (paymentResponse.pay_state()) {
                case 1:
                        break;
                case 4:
                        System.out.println("결제완료 처리 시작");
                        // TODO: ex) updatePayRequestStatus("결제완료", request.pay_date(), request.var1(), request.mul_no());
                        break;
                case 8:
                case 16:
                case 32:

                        System.out.println("결제요청 취소 처리");
                        // TODO: 취소 처리 로직 추가
                        break;
                case 9:
                case 64:

                        System.out.println("결제승인 취소 처리");
                        // TODO: ex) updatePayRequestStatus("결제취소", request.pay_date(), request.var1(), request.mul_no());
                        break;
                case 10:
                        System.out.println("결제대기 상태");
                        break;
                case 70:
                case 71:
                        System.out.println("결제부분취소 처리");
                        // TODO: ex) updatePayRequestStatus("결제취소", request.canceldate(), request.var1(), request.mul_no());
                        break;
                default:
                        System.out.println("Unknown pay_state: " + paymentResponse.pay_state());
                        break;
        }

        return ResponseEntity.ok("SUCCESS");
    }
}
