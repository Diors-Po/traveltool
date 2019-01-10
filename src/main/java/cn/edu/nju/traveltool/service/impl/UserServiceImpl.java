package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.exception.*;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.UserRepository;
import cn.edu.nju.traveltool.service.UserService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ReponseMessage login(UserVO userVO) {
        User u = userWrapper.unwrapper(userVO);
        u = userRepository.findByEmailAndPwd(u.getEmail(),u.getPwd());
        if (u==null)
            throw new PwdErrorException();
        return ReponseMessage.OK;
    }

    @Override
    public ReponseMessage register(UserVO userVO) {
        User u = userWrapper.unwrapper(userVO);
        if(userRepository.findByEmail(u.getEmail())!=null)
            throw new UserHasPresenceException();

        userRepository.save(u);
        return ReponseMessage.OK;
    }
}
