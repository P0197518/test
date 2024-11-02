package com.fujias.common.security.springsecurity.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fujias.common.util.Sha256Util;

/**
 * Sha256PasswordEncoderの定義クラスです。
 * 
 * @author 陳強
 *
 */
public class Sha256PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return Sha256Util.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

}
