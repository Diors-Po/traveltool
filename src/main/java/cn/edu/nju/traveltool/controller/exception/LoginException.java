package cn.edu.nju.traveltool.controller.exception;

import lombok.NoArgsConstructor;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 23:11
 **/
@NoArgsConstructor
public class LoginException extends UserException {

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
