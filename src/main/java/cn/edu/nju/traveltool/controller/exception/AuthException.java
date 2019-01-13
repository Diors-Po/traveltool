package cn.edu.nju.traveltool.controller.exception;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 22:47
 **/
public class AuthException extends UserException{
    public AuthException() {
    }

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
