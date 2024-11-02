package com.fujias.common.util;

import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.fujias.common.exception.UsernameIsExitedException;
import com.fujias.common.redis.BusinessCacheUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token 鬪瑚ｯ∝ｷ･蜈ｷ邀ｻ
 *
 * @author chenqiang
 **/
public final class TokenAuthenticationUtil {

    /**
     * 蟄俶叛Token逧Зeader Key
     */
    public static final String HEADER_STRING = "X-Token";

    /**
     * JWT 蟇�遐�
     */
    private static final String SECRET = "testKey";
    /**
     * TOKEN蜑咲ｼ�
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 閾ｪ螳壻ｹ臥噪 playload
     */
    private static final String AUTHORITIES = "authorities";

    private TokenAuthenticationUtil() {

    }

    /**
     * 蟆�jwt token 蜀吝�･header螟ｴ驛ｨ
     *
     * @param authentication authentication
     * @return String token
     */
    public static String getAuthenticatiotoToken(Authentication authentication) {

        StringBuilder rolesStringBuilder = new StringBuilder("");

        for (GrantedAuthority authorityItem : authentication.getAuthorities()) {
            rolesStringBuilder.append(authorityItem.getAuthority());
            rolesStringBuilder.append(",");
        }
        String rolesString = "";
        if (rolesStringBuilder.length() > 1) {
            rolesString = rolesStringBuilder.substring(0, rolesStringBuilder.length() - 1);
        }
        // 逕滓�� jwt
        String token = Jwts.builder()
                        // 逕滓�腎oken逧�譌ｶ蛟吝庄莉･謚願�ｪ螳壻ｹ画焚謐ｮ蜉�霑帛悉,豈泌ｦら畑謌ｷ譚�髯�
                        .claim(AUTHORITIES, rolesString).setSubject(authentication.getPrincipal().toString())
                        .setExpiration(new Date(System.currentTimeMillis() + BusinessCacheUtil.LOGIN_EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        return token;
    }

    /**
     * 莉手ｯｷ豎ょ､ｴ荳ｭ隗｣譫仙�ｺ Authentication
     * 
     * @param token token
     * @return Authentication鬪瑚ｯ∽ｿ｡諱ｯ
     */
    public static Authentication getAuthentication(String token) {
        // 莉皐eader荳ｭ諡ｿ蛻ｰtoken
        if (token == null) {
            return null;
        }

        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

        // 蠕怜芦 譚�髯撰ｼ郁ｧ定牡�ｼ�
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));

        // 蠕怜芦逕ｨ謌ｷ蜷�
        String username = claims.getSubject();

        // 蠕怜芦霑�譛滓慮髣ｴ
        Date expiration = claims.getExpiration();

        // 蛻､譁ｭ譏ｯ蜷ｦ霑�譛�
        Date now = new Date();

        if (now.getTime() > expiration.getTime()) {

            throw new UsernameIsExitedException("隸･雍ｦ蜿ｷ蟾ｲ霑�譛�,隸ｷ驥肴眠逋ｻ髯�");
        }

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;

    }

}
