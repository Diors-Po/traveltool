package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.controller.exception.AuthException;
import cn.edu.nju.traveltool.data.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 22:05
 **/
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (hasLoginRequired(handler,request))
            return true;
        throw new AuthException();
    }

    private boolean hasLoginRequired(Object handler,HttpServletRequest request) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
            if(loginRequired==null){
                loginRequired = handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
            }
            //需要验证session是否有相应的权限
            if(loginRequired!=null){
                UserDTO u = (UserDTO)request.getSession().getAttribute("user");
                if(u==null) return false;
            }
        }
        return true;
    }
}
