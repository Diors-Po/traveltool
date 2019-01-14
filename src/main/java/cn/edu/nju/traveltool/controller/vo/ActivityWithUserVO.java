package cn.edu.nju.traveltool.controller.vo;

import cn.edu.nju.traveltool.entity.ActivityWithUser;
import lombok.Data;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 17:16
 **/
@Data
public class ActivityWithUserVO {
    private long userId;
    private ActivityWithUser.Status status;
    private long activityId;
    private String reason;
    private ActivityVO activityVO;
    private UserVO userVO;
}
