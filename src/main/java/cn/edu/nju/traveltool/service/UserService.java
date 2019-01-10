package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:45
 **/
public interface UserService {
    ReponseMessage login(UserVO userVO);
    ReponseMessage register(UserVO userVO);
}
