package com.pangapiserver.infrastructure.encode;

import com.pangapiserver.infrastructure.encode.exception.EncodeException;

import java.security.MessageDigest;

public class Sha512Encoder {
    public static String encode(String raw) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(raw.getBytes());

            for (byte byteDatum : md.digest()) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new EncodeException();
        }
    }

    public static boolean match(String raw, String encoded) {
        return encode(raw).equals(encoded);
    }
}
