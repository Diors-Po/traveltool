package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.ActivityListVO;
import cn.edu.nju.traveltool.controller.vo.ActivityWithUserVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.entity.Activity;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityRespository;
import cn.edu.nju.traveltool.service.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 17:21
 **/

@Service
public class ActivityWithUserWrapper {

    @Autowired
    private UserWrapper userWrapper;
    @Autowired
    private ActivityWrapper activityWrapper;
    @Autowired
    private ActivityRespository activityRespository;
    public ActivityWithUser unwrapper(long userId, long activityId){
        ActivityWithUser activityWithUser = new ActivityWithUser();
        activityWithUser.setActivityId(activityId);
        activityWithUser.setUserId(userId);
        activityWithUser.setStatus(ActivityWithUser.Status.OWNER);
        return activityWithUser;
    }

    public ActivityWithUser unwrapper(JoinActivityVO joinActivityVO){
        ActivityWithUser activityWithUser = new ActivityWithUser();
        activityWithUser.setStatus(ActivityWithUser.Status.PREMEMBER);
        activityWithUser.setUserId(joinActivityVO.getUserId());
        activityWithUser.setActivityId(joinActivityVO.getActivityId());
        activityWithUser.setReason(joinActivityVO.getReason());
        return activityWithUser;
    }

    public ActivityWithUserVO wrapper(ActivityWithUser activityWithUser){
        ActivityWithUserVO activityWithUserVO = new ActivityWithUserVO();
        BeanUtils.copyProperties(activityWithUser,activityWithUserVO);
        return activityWithUserVO;
    }

    public ActivityWithUserVO wrapper(ActivityWithUser activityWithUser, User user){
        ActivityWithUserVO activityWithUserVO = new ActivityWithUserVO();
        BeanUtils.copyProperties(activityWithUser,activityWithUserVO);
        Activity activity = activityRespository.findById(activityWithUser.getActivityId()).get();
        activityWithUserVO.setActivityVO(activityWrapper.wrapper(activity));
        activityWithUserVO.setUserVO(userWrapper.wrapper(user));
        return activityWithUserVO;
    }

}