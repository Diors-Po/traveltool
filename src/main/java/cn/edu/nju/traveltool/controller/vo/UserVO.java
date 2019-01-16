package cn.edu.nju.traveltool.controller.vo;

import cn.edu.nju.traveltool.entity.ActivityWithUser;
import lombok.Data;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-10 22:42
 **/
@Data
public class UserVO {
    private long id;
    private String email;
    private String pwd;
    private String user;
    private ActivityWithUser.Status role;
}
