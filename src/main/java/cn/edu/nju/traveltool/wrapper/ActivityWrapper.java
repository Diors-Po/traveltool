package cn.edu.nju.traveltool.wrapper;

import cn.edu.nju.traveltool.controller.vo.ActivityInfoVO;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.NoticeVO;
import cn.edu.nju.traveltool.controller.vo.UserVO;
import cn.edu.nju.traveltool.entity.Activity;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityWithUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:43
 **/
@Service
public class ActivityWrapper {
    @Autowired
    private ActivityWithUserRepository activityWithUserRepository;
    @Autowired
    private UserWrapper userWrapper;
    public Activity unwrapper(ActivityVO activityVO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityVO,activity);
        return activity;
    }

    public ActivityVO wrapper(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity,activityVO);
        return activityVO;
    }

    public ActivityInfoVO wrapper(List<NoticeVO> noticeVOList, List<UserVO> userVOList, Activity activity, User user){
        ActivityInfoVO activityInfoVO = new ActivityInfoVO();
        activityInfoVO.setNoticeVOList(noticeVOList);
        activityInfoVO.setUserVOList(userVOList);
        activityInfoVO.setActivityVO(wrapper(activity));
        ActivityWithUser activityWithUser  = activityWithUserRepository.findFirstByActivityIdAndUserId(activity.getId(),user.getId());
        UserVO userVO = userWrapper.wrapper(user);
        if(activityWithUser != null)
            userVO.setRole(activityWithUser.getStatus());
        activityInfoVO.setUserVO(userVO);
        return activityInfoVO;
    }
}
