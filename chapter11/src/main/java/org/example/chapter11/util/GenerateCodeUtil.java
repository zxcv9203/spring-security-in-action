package org.example.chapter11.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {

    private GenerateCodeUtil() {}

    public static String generateCode() {
        String code;

        try {
            // 임의 int 값을 생성하는 SecureRandom 인스턴스 생성
            SecureRandom random = SecureRandom.getInstanceStrong();
            // 0 ~ 8999 사이의 값을 생성하고 1000을 더해서 1000 ~ 9999 사이의 값을 얻습니다.
            int c = random.nextInt(9000) + 1000;
            // int를 String으로 변환하고 반환합니다.
            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }
        return code;
    }
}
