package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.exception.AuthException;
import cn.edu.nju.traveltool.controller.exception.LoginException;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        if (hasLoginRequired(handler,request))
            return true;
        throw new AuthException();
    }

    private boolean hasLoginRequired(Object handler,HttpServletRequest request) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
            if(loginRequired==null){
                loginRequired = handlerMethod.getClass().getAnnotation(LoginRequired.class);
            }
            //需要验证session是否有相应的权限
            if(loginRequired!=null){
                User u = (User)request.getSession().getAttribute("user");
                if(u==null) return false;
            }
        }
        return true;
    }
}
