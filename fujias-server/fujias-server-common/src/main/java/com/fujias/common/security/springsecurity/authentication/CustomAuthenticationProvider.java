package com.fujias.common.security.springsecurity.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.fujias.common.constants.MessageCodes;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.util.Sha256Util;

/**
 * 検証のProviderクラスです。詳細の登録検証ロジックは当該クラスに実装する。
 * 
 * @author 陳強
 *
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomUsernamePasswordAuthenticationToken authenticationToken = (CustomUsernamePasswordAuthenticationToken) authentication;

        if (authenticationToken.getPrincipal() == null || authenticationToken.getPrincipal().equals("")
                        || authenticationToken.getCredentials() == null || authenticationToken.getCredentials().equals("")) {
            throw new BadCredentialsException(MessageCodes.EC0016);
        }
        UserDetails userInfo = getUserDetailsService().loadUserByUsername(authenticationToken.getPrincipal().toString());
        if (userInfo == null) {
            throw new BadCredentialsException(MessageCodes.EC0016);
        }

        LoginUser loginUser = (LoginUser) userInfo;

        String inputPass = Sha256Util.encode(authenticationToken.getCredentials().toString());

        if (!loginUser.getUsername().equals(authenticationToken.getPrincipal()) || !loginUser.getPassword().equals(inputPass)) {
            throw new BadCredentialsException(MessageCodes.EC0016);
        }

        // 検証成功の場合、検証情報を作成して返却する
        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
