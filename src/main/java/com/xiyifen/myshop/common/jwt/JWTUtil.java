package com.xiyifen.myshop.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.xiyifen.myshop.common.property.ShiroProperty;
import com.xiyifen.myshop.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.AuthenticationException;
import java.util.Date;

/**
 * @Author: fs.z
 * @Date: 2019/3/26 22:49
 * @Description: JWT工具类：github--> https://github.com/auth0/java-jwt
 */
@Slf4j
public class JWTUtil {



    // token 过期时间 单位毫秒 1s=1000ms
    private static final long EXPIRE_TIME =  SpringContextUtil.getBean(ShiroProperty.class).getJwtTimeOut() * 1000;
//    private static final long EXPIRE_TIME =  1800 * 1000;

    /**
     * 检验token是否正确
     *
     * @param token    需要校验的token
     * @param username 用户名
     * @param secret   用户的密码
     * @return
     */
    public static boolean verify(String token, String username, String secret)  {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.error("token is invalid:{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从token 中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String username = decode.getClaim("username").asString();
            return username;
        } catch (JWTDecodeException e) {
            log.error("error: {}", e.getMessage());
            return null;
        }
    }

    /**
     * @param username 用户名
     * @param secret   密码
     * @return
     */
    public static String sign(String username, String secret) {


//        final long EXPIRE_TIME =  SpringContextUtil.getApplicationContext().getBean(ShiroProperty.class).getJwtTimeOut() * 1000;
//        ShiroProperty shiroProperty=new ShiroProperty();
//        System.out.println(shiroProperty);

        try {
//        username = StringUtils.lowerCase(username);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            return JWT.create().withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return null;
        }

    }
}






























