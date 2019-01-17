package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.vo.*;
import cn.edu.nju.traveltool.entity.Activity;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityRespository;
import cn.edu.nju.traveltool.repository.ActivityWithUserRepository;
import cn.edu.nju.traveltool.repository.NoticeRepository;
import cn.edu.nju.traveltool.repository.UserRepository;
import cn.edu.nju.traveltool.service.ActivityService;
import cn.edu.nju.traveltool.wrapper.ActivityWithUserWrapper;
import cn.edu.nju.traveltool.wrapper.ActivityWrapper;
import cn.edu.nju.traveltool.wrapper.NoticeWrapper;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 14:20
 **/
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRespository activityRespository;
    @Autowired
    private ActivityWithUserRepository activityWithUserRepository;
    @Autowired
    private ActivityWrapper activityWrapper;
    @Autowired
    private ActivityWithUserWrapper activityWithUserWrapper;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeWrapper noticeWrapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserWrapper userWrapper;
    @Override
    public ActivityVO insertActivity(ActivityVO activityVO) {
        Activity activity = activityWrapper.unwrapper(activityVO);
        activity = activityRespository.save(activity);
        activityVO.setId(activity.getId());
        ActivityWithUser activityWithUser = activityWithUserWrapper.unwrapper(activityVO.getUserId(),activityVO.getId());
        activityWithUserRepository.save(activityWithUser);
        return activityVO;
    }

    @Override
    public void updateActivity(ActivityVO activityVO) {
        Activity activity = activityWrapper.unwrapper(activityVO);
        activityRespository.save(activity);
    }

    @Override
    public List<ActivityWithUserVO> listActivity(User user) {

        List<ActivityWithUser> activityWithUsers = activityWithUserRepository.findAllByUserId(user.getId(),new Sort(Sort.Direction.DESC,"id"));
        return activityWithUsers.stream().map(x -> activityWithUserWrapper.wrapper(x,user)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityWithUserVO> listActivity(User user, ActivityWithUser.Status status) {
        return listActivity(user).stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public List<ActivityWithUserVO> listActivity(User user, List<ActivityWithUser.Status> status) {
        return listActivity(user).stream().filter(x -> status.contains(x.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<ActivityWithUserVO> listUsersByActivityId(User user, long activityId) {
        return activityWithUserRepository.findActivityWithUserByActivityIdAndStatusIn(activityId, Lists.newArrayList(ActivityWithUser.Status.PREMEMBER))
                .stream().map(x-> activityWithUserWrapper.wrapper2(x,userRepository.findById(x.getUserId()).orElse(new User()))).collect(Collectors.toList());
    }

    @Override
    public void closedActivity(ActivityVO activityVO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void modifyUserActivity(JoinActivityVO joinActivityVO, ActivityWithUser.Status status) {
        ActivityWithUser activityWithUser = activityWithUserRepository.findFirstByActivityIdAndUserId(joinActivityVO.getActivityId(),joinActivityVO.getUserId());

        if(activityWithUser == null){
            activityWithUser = activityWithUserWrapper.unwrapper(joinActivityVO,status);
            activityWithUserRepository.save(activityWithUser);
        }else{
            activityWithUser = activityWithUserWrapper.unwrapper(activityWithUser,joinActivityVO,status);
            activityWithUserRepository.save(activityWithUser);
        }
    }

    @Override
    public void modifyUserActivity(long activityId, long userId, ActivityWithUser.Status status) {
        JoinActivityVO joinActivityVO = new JoinActivityVO();
        joinActivityVO.setActivityId(activityId);
        joinActivityVO.setUserId(userId);
        modifyUserActivity(joinActivityVO,status);
    }

    @Override
    public ActivityInfoVO activityInfo(User user, long activityId) {
        List<NoticeVO> noticeVOList = noticeRepository.findByActivityIdAndUserId(activityId,user.getId(),new Sort(Sort.Direction.DESC,"id")).stream().map(x -> noticeWrapper.wrapper(x)).collect(Collectors.toList());
        List<Long> userIds = activityWithUserRepository.findActivityWithUserByActivityIdAndStatusIn(activityId, Lists.newArrayList(ActivityWithUser.Status.OWNER,ActivityWithUser.Status.MEMBER))
                .stream().map(ActivityWithUser::getUserId).collect(Collectors.toList());
        List<UserVO> userVOList = userRepository.findByIdIn(userIds).stream().map(x -> userWrapper.wrapperWithRole(x,activityId)).collect(Collectors.toList());

        Activity activity = activityRespository.findById(activityId).orElse(new Activity());
        return activityWrapper.wrapper(noticeVOList,userVOList,activity,user);
    }

    @Override
    public ActivityVO activityInfo(long activityId) {
        Activity activity = activityRespository.findById(activityId).orElse(new Activity());
        return activityWrapper.wrapper(activity);
    }
}
