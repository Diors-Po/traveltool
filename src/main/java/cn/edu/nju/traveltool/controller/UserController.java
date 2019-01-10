package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:54
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("login")
    public ReponseMessage login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }
    @PostMapping("register")
    public ReponseMessage register(@RequestBody UserVO userVO) {
        return userService.register(userVO);
    }
}
