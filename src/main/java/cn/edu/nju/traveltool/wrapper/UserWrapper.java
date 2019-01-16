package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.data.UserDTO;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityWithUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ActivityWithUserRepository activityWithUserRepository;

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

    public UserVO wrapperWithRole(User user,long activityId){
        ActivityWithUser activityWithUser = activityWithUserRepository.findFirstByActivityIdAndUserId(activityId,user.getId());
        UserVO u = new UserVO();
        BeanUtils.copyProperties(user,u);
        u.setPwd("******");
        u.setUser(user.getNickname());
        u.setRole(activityWithUser.getStatus());
        return u;
    }

    public UserDTO wrapper2DTO(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        return userDTO;
    }

    public User unwrapperFromDTO(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
}
