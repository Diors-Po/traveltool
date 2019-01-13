package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import org.springframework.stereotype.Service;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 17:21
 **/

@Service
public class ActivityWithUserWrapper {

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
}
