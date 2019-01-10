package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.exception.PwdErrorException;
import cn.edu.nju.traveltool.controller.exception.RegisterException;
import cn.edu.nju.traveltool.controller.exception.UserException;
import cn.edu.nju.traveltool.controller.exception.UserHasPresenceException;
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
            return new ReponseMessage(400,Constant.USER_PWD_ERROR);
        }else{
            return new ReponseMessage(400,Constant.USER_LOGIN_FAIL);
        }
    }
}
