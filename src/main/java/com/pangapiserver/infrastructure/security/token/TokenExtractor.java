package com.pangapiserver.infrastructure.security.token;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
public class TokenExtractor {
    private static final String type = "Bearer ";

    public String extract(Enumeration<String> headers) {
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.startsWith(type)) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }
}
