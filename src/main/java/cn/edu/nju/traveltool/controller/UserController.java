package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public ReponseMessage login(@RequestBody UserVO userVO, HttpSession httpSession) {
        return userService.login(userVO,httpSession);
    }
    @PostMapping("register")
    public ReponseMessage register(@RequestBody UserVO userVO, HttpSession httpSession) {
        return userService.register(userVO,httpSession);
    }

    @GetMapping("info")
    @LoginRequired
    public ReponseMessage<UserVO> info(@CurrentUser User user){
        return userService.info(user);
    }
}
