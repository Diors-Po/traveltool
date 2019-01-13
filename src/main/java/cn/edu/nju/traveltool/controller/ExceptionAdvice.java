package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.exception.*;
import cn.edu.nju.traveltool.data.ReponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:59
 **/
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReponseMessage loginException(UserException e) {
        if (e instanceof UserHasPresenceException){
            return new ReponseMessage(400, Constant.USER_HAS_PRESENCE);
        }else if(e instanceof RegisterException){
            return new ReponseMessage(500,Constant.USER_REGISTER_FAIL);
        }else if(e instanceof PwdErrorException){
            return new ReponseMessage(400,Constant.USER_LOGIN_ERROR);
        }else if(e instanceof AuthException){
            return new ReponseMessage(401,Constant.AUTH_FAIL);
        }else{
            return new ReponseMessage(400,Constant.USER_LOGIN_FAIL);
        }
    }
}
