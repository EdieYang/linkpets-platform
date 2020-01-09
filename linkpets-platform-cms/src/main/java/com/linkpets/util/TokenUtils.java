package com.linkpets.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.linkpets.core.model.SysUser;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @Author Edie
 */
public class TokenUtils {

    private static final Integer TOKEN_EXPIRE_HOUR = 24;

    public static String getToken(SysUser user) {
        return JWT.create().withAudience(user.getUserId()).withExpiresAt(DateUtils.addHours(new Date(), TOKEN_EXPIRE_HOUR)).sign(Algorithm.HMAC256(user.getPassword()));
    }

}
