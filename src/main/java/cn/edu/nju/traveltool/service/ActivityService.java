package cn.edu.nju.traveltool.service;

import cn.edu.nju.traveltool.controller.vo.*;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:19
 **/
public interface ActivityService {
    ActivityVO insertActivity(ActivityVO activityVO);
    void updateActivity(ActivityVO activityVO);
    List<ActivityWithUserVO> listActivity(User user);
    List<ActivityWithUserVO> listActivity(User user, ActivityWithUser.Status status);
    List<ActivityWithUserVO> listActivity(User user, List<ActivityWithUser.Status> status);
    void closedActivity(ActivityVO activityVO);

    void joinActivity(JoinActivityVO joinActivityVO);

    ActivityInfoVO activityInfo(User user,long activityId);
}
