package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.exception.PwdErrorException;
import cn.edu.nju.traveltool.controller.exception.UserHasPresenceException;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.UserRepository;
import cn.edu.nju.traveltool.service.UserService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:45
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWrapper userWrapper;
    @Override
    public ReponseMessage<UserVO> login(UserVO userVO, HttpSession httpSession) {
        User u = userWrapper.unwrapper(userVO);
        u = userRepository.findByEmailAndPwd(u.getEmail(),u.getPwd());
        if (u==null)
            throw new PwdErrorException();
        if(httpSession.getAttribute("user") == null){
            httpSession.setAttribute("user",userWrapper.wrapper2DTO(u));
        }
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,userWrapper.wrapper(u));
    }

    @Override
    public ReponseMessage<UserVO> register(UserVO userVO,HttpSession httpSession) {
        User u = userWrapper.unwrapper(userVO);
        if(userRepository.findByEmail(u.getEmail())!=null)
            throw new UserHasPresenceException();

        u = userRepository.save(u);
        //注册过程后也相当于登录了
        httpSession.setAttribute("user",userWrapper.wrapper2DTO(u));

        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,userWrapper.wrapper(u));
    }

    @Override
    public ReponseMessage<UserVO> info(User user) {
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,userWrapper.wrapper(user));
    }
}
