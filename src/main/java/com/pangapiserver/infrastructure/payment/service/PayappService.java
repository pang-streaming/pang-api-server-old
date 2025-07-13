package com.pangapiserver.infrastructure.payment.service;

import com.pangapiserver.infrastructure.payment.dto.PaymentCardResponse;
import com.pangapiserver.infrastructure.payment.dto.RegisterCardResponse;
import com.pangapiserver.infrastructure.payment.properties.PaymentProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class PayappService {
    private final WebClient payappWebClient;
    private final PaymentProperties paymentProperties;

    public PaymentCardResponse paymentCard(
        String encBill,
        String goodName,
        String price,
        String recvPhone
    ) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cmd", "billPay");
        formData.add("userid", paymentProperties.getSellerid());
        formData.add("encBill", encBill);
        formData.add("goodname", goodName);
        formData.add("price", price);
        formData.add("recvphone", recvPhone);

        String response = payappWebClient.post()
            .uri("/oapi/apiLoad.html")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (response == null) {
            throw new RuntimeException("Null response from PayApp");
        }

        Map<String, String> map = parseQueryString(response);

        return new PaymentCardResponse(
            decode(map.get("state")),
            decode(map.get("errorMessage")),
            decode(map.get("CSTURL")),
            decode(map.get("price")),
            decode(map.get("mul_no"))
        );
    }

    public RegisterCardResponse registerCard(
        String userId,
        String cardNumber,
        String expYear,
        String expMonth,
        String authNumber,
        String cardPw,
        String phone,
        String name
    ) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cmd", "billRegist");
        formData.add("userid", paymentProperties.getSellerid());
        formData.add("cardNo", cardNumber);
        formData.add("expYear", expYear);
        formData.add("expMonth", expMonth);
        formData.add("buyerAuthNo", authNumber);
        formData.add("cardPw", cardPw);
        formData.add("buyerPhone", phone);
        formData.add("buyerName", name);
        formData.add("buyerId", userId);

        String response = payappWebClient.post()
            .uri("/oapi/apiLoad.html")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if (response == null) {
            throw new RuntimeException("Null response from PayApp");
        }

        Map<String, String> map = parseQueryString(response);

        return new RegisterCardResponse(
            decode(map.get("state")),
            decode(map.get("errorMessage")),
            decode(map.get("errno")),
            decode(map.get("errnoDetail")),
            decode(map.get("encBill")),
            decode(map.get("billAuthNo")),
            decode(map.get("cardno")),
            decode(map.get("cardname")),
            decode(map.get("remoteaddr"))
        );
    }

    private static Map<String, String> parseQueryString(String query) {
        Map<String, String> map = new LinkedHashMap<>();
        Arrays.stream(query.split("&"))
            .forEach(pair -> {
                int idx = pair.indexOf('=');
                String key = (idx > 0) ? pair.substring(0, idx) : pair;
                String value = (idx > 0 && pair.length() > idx + 1) ? pair.substring(idx + 1) : "";
                map.put(key, value);
            });
        return map;
    }

    private static String decode(String value) {
        return value != null ? URLDecoder.decode(value, StandardCharsets.UTF_8) : null;
    }
}
