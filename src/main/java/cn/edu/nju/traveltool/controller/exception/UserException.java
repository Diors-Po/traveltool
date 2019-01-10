package cn.edu.nju.traveltool.controller.exception;

import lombok.NoArgsConstructor;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 23:10
 **/
@NoArgsConstructor
public class UserException extends RuntimeException {

    UserException(String msg) {
        super(msg);
    }

    UserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
