package com.pangapiserver.application.payment;

import com.pangapiserver.application.payment.data.PaymentRequest;
import com.pangapiserver.application.payment.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {
        private final PaymentProperties properties;

        private Boolean isMatchingCredentials(String linkkey, String linkval) {
                return properties.getLinkkey().equals(linkkey) &&
                        properties.getLinkval().equals(linkval);
        }

        public ResponseEntity callback(PaymentRequest paymentResponse) {
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
