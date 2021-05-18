package com.juzipi.commonutil.util;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtils {

    //过期时间
    private static long tokenExpiration = 60*1000*10;
    //签名秘钥
    private static String tokenSignKey = "kwausdgajhsdgjhasd";


    //根据参数生成token
    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                .setSubject("YYS-USER")//主题
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //荷载
                .claim("userId", userId)
                .claim("userName", userName)
                //加密方式
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //根据token字符串得到用户id
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    //根据token字符串得到用户名称
    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)){
            return "";
        }

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    public static void main(String[] args) {
        String token = JwtUtils.createToken(1L, "lucy");
        System.out.println(token);
        System.out.println(JwtUtils.getUserId(token));
        System.out.println(JwtUtils.getUserName(token));
    }
}

