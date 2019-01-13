package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 23:31
 **/
@Service
public class UserWrapper {

    public User unwrapper(UserVO userVO){
        User u = new User();
        BeanUtils.copyProperties(userVO,u);
        u.setNickname(userVO.getUser());
        u.setPwd(DigestUtils.md5DigestAsHex(u.getPwd().getBytes())); //md5密码
        return u;
    }

    public UserVO wrapper(User user){
        UserVO u = new UserVO();
        BeanUtils.copyProperties(user,u);
        u.setPwd("******");
        u.setUser(user.getNickname());
        return u;
    }

}
