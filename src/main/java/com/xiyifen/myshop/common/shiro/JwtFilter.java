package com.xiyifen.myshop.common.shiro;

import com.xiyifen.myshop.common.jwt.JWTToken;
import com.xiyifen.myshop.common.property.ShiroProperty;
import com.xiyifen.myshop.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义拦截器
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

//    private static final String TOKEN = "Authentication";
    private static final String TOKEN = "Authorization";

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private ShiroProperty shiroProperty;

    /**
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws UnauthorizedException
     *  所有的请求都会进入到此method，除了执行不拦截的url，其余所有的请求都会尝试去登录，执行executeLogin()
     *  实际上当访问/login请求时，只是生成了token并返回给前端。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 先通过SpringContextUtil获取bean
        ShiroProperty shiroProperty = SpringContextUtil.getBean(ShiroProperty.class);
        String[] anonUrl = shiroProperty.getAnonUrl().split(",");
//
        boolean match = false;
        for (String u : anonUrl) {
            //  将两个参数匹配，返回bolean值
            if (pathMatcher.match(u, httpServletRequest.getRequestURI()))
                match = true;
        }
        if (match)
            return true;
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    /**
     *  isLoginAttempt 返回false时，跳转到sendChallenge()，
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        //  不需要解密  在登录的时候没有对token进行加密RbacUtil.encryptToken
//        String Token = RbacUtil.decryptToken(token);
        JWTToken jwtToken = new JWTToken(token);
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (AuthenticationException e) {
            log.error("huhuhuhu111"+e.getMessage());
            return false;
        }

    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 跳转到sendChallenge处理token验证不通过时返回给前端信息。
     * 顺便解决了访问swagger时弹出登录框的问题。是由于http://localhost:port/ 被拦截了
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        log.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        final String message = "未认证，请在前端系统进行认证";
        try (PrintWriter out = httpResponse.getWriter()) {
            String responseJson = "{\"message\":\"" + message + "\"}";
            out.print(responseJson);
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
        return false;
    }
}
