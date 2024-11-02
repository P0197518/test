package com.fujias.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;

/**
 * SHA256暗号化ツールクラス
 * 
 * @author 陳強
 *
 */
public final class Sha256Util {

    private Sha256Util() {

    }

    /**
     * 暗号化を行う。64桁の結果を返却する。
     * 
     * @param src src
     * @return 暗号化された結果
     */
    public static String encode(String src) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(src.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encode(hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
