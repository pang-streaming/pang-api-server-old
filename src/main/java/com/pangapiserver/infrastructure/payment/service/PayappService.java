package com.pangapiserver.infrastructure.payment.service;

import com.pangapiserver.infrastructure.payment.dto.PaymentCardResponse;
import com.pangapiserver.infrastructure.payment.dto.RegisterCardResponse;
import com.pangapiserver.infrastructure.payment.properties.PaymentProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class PayappService {
    private final PaymentProperties paymentProperties;

    public PayappService(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

    public PaymentCardResponse paymentCard(
        String encBill,
        String goodName,
        String price,
        String recvPhone
    ) {
        WebClient webClient = WebClient.create("https://api.payapp.kr");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cmd", "billPay");
        formData.add("userid", paymentProperties.getSellerid());
        formData.add("encBill", encBill);
        formData.add("goodname", goodName);
        formData.add("price", price);
        formData.add("recvphone", recvPhone);

        Mono<String> responseMono = webClient.post()
            .uri("/oapi/apiLoad.html")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(String.class);

        String response = responseMono.block();

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
        String user_id,
        String card_number,
        String exp_year,
        String exp_month,
        String auth_number,
        String card_pw,
        String phone,
        String name
    ) {
        WebClient webClient = WebClient.create("https://api.payapp.kr");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("cmd", "billRegist");
        formData.add("userid", paymentProperties.getSellerid());
        formData.add("cardNo", card_number);
        formData.add("expYear", exp_year);
        formData.add("expMonth", exp_month);
        formData.add("buyerAuthNo", auth_number);
        formData.add("cardPw", card_pw);
        formData.add("buyerPhone", phone);
        formData.add("buyerName", name);
        formData.add("buyerId", user_id);

        Mono<String> responseMono = webClient.post()
            .uri("/oapi/apiLoad.html")
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(String.class);

        String response = responseMono.block();

        if (response == null) {
            throw new RuntimeException("Null response from PayApp");
        }

        Map<String, String> map = parseQueryString(response);

        return RegisterCardResponse.builder()
            .state(decode(map.get("state")))
            .errorMessage(decode(map.get("errorMessage")))
            .errno(decode(map.get("errno")))
            .errnoDetail(decode(map.get("errnoDetail")))
            .encBill(decode(map.get("encBill")))
            .billAuthNo(decode(map.get("billAuthNo")))
            .cardno(decode(map.get("cardno")))
            .cardname(decode(map.get("cardname")))
            .remoteaddr(decode(map.get("remoteaddr")))
            .build();
    }

    private static Map<String, String> parseQueryString(String query) {
            Map<String, String> map = new LinkedHashMap<>();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                    int idx = pair.indexOf('=');
                    String key = idx > 0 ? pair.substring(0, idx) : pair;
                    String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : "";
                    map.put(key, value);
            }
            return map;
    }

    private static String decode(String value) {
            return value != null ? URLDecoder.decode(value, StandardCharsets.UTF_8) : null;
    }
}
