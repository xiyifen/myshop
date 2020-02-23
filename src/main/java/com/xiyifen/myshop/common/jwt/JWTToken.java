package com.xiyifen.myshop.common.jwt;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token
 */
public class JWTToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -2097914691703953518L;

    private String token;

    private String exipreAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String exipreAt) {
        this.token = token;
        this.exipreAt = exipreAt;
    }


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
