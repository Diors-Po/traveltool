package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:45
 **/
public interface UserService {
    ReponseMessage<UserVO> login(UserVO userVO, HttpSession httpSession);
    ReponseMessage<UserVO> register(UserVO userVO, HttpSession httpSession);
    ReponseMessage<UserVO> info(User user);
}
