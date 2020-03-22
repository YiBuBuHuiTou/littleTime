package com.cxd.littletime.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTUtils {

    private static final String SECRET = "howareyoufinethankyouandyou";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 生成JWT   Token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        //生成时的时间戳
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        //生成jwt builder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).
                setIssuer("littleTime").setIssuedAt(nowDate).setSubject(subject).
                signWith(signatureAlgorithm, getSecurityKey());

        if (ttlMillis > 0) {
            long expireMillis = nowMillis + ttlMillis * 1000;
            Date expireDate = new Date(expireMillis);
            jwtBuilder.setExpiration(expireDate);
        }
        // 返回token
        return jwtBuilder.compact();
    }

    /**
     * 更新token 过期时间
     * @param token
     * @param ttlMills
     * @return
     */
    public static String refreshJWT(String token, long ttlMills) {
        long nowMillis = System.currentTimeMillis();
        Claims claims = parseJWT(token);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).
                setExpiration(new Date(nowMillis + ttlMills)).
                signWith(signatureAlgorithm, getSecurityKey());
        return jwtBuilder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseJWT(String token) {
        return Jwts.parser().setSigningKey(getSecurityKey()).parseClaimsJws(token).getBody();
    }

    /**
     * 校验是否过期
     * @param token
     * @return
     */
    public static boolean validateJWT(String token) {
        Claims claims = parseJWT(token);
        if (claims != null) {
             Date expireDate = claims.getExpiration();
             if (System.currentTimeMillis() <= expireDate.getTime()) {
                return true;
             }
        }
        return false;
    }


    //生成秘钥
    private static Key getSecurityKey() {
        byte[] keySecurityByte = DatatypeConverter.parseBase64Binary(SECRET);
        return new SecretKeySpec(keySecurityByte,SignatureAlgorithm.HS256.getJcaName());
    }
}
