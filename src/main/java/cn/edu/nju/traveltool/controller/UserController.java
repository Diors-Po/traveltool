package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.data.UserDTO;
import cn.edu.nju.traveltool.service.UserService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private UserWrapper userWrapper;

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
    public ReponseMessage<UserVO> info(@CurrentUser UserDTO user){

        return userService.info(userWrapper.unwrapperFromDTO(user));
    }

    @GetMapping("logout")
    public ReponseMessage logout(HttpSession httpSession){
        if(httpSession.getAttribute("user")!=null){
            httpSession.removeAttribute("user");
        }
        return ReponseMessage.OK;
    }
}
