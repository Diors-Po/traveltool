package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.vo.*;
import cn.edu.nju.traveltool.entity.Activity;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.Notice;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ActivityWithUserVO> listActivity(int page, int size, User user) {
        Pageable pageable = PageRequest.of(page,size,new Sort(Sort.Direction.DESC,"id"));
        Page<ActivityWithUser> activityWithUsers = activityWithUserRepository.findAllByUserId(user.getId(),pageable);
        Page<ActivityWithUserVO>  activityWithUserVOS = activityWithUsers.map(x -> activityWithUserWrapper.wrapper(x,user));
        return activityWithUserVOS;
    }

    @Override
    public void closedActivity(ActivityVO activityVO) {

    }

    @Override
    public void joinActivity(JoinActivityVO joinActivityVO) {
        ActivityWithUser activityWithUser = activityWithUserWrapper.unwrapper(joinActivityVO);
        activityWithUserRepository.save(activityWithUser);
    }

    @Override
    public ActivityInfoVO activityInfo(User user, long activityId) {
        List<NoticeVO> noticeVOList = noticeRepository.findByActivityIdAndUserId(activityId,user.getId(),new Sort(Sort.Direction.DESC,"id")).stream().map(x -> noticeWrapper.wrapper(x)).collect(Collectors.toList());

        List<Long> userIds = activityWithUserRepository.findActivityWithUserByActivityIdAndStatusIn(activityId, Lists.newArrayList(ActivityWithUser.Status.OWNER,ActivityWithUser.Status.PREMEMBER))
                .stream().map(ActivityWithUser::getUserId).collect(Collectors.toList());
        List<UserVO> userVOList = userRepository.findByIdIn(userIds).stream().map(x -> userWrapper.wrapper(x)).collect(Collectors.toList());
        Activity activity = activityRespository.findById(activityId).get();
        return activityWrapper.wrapper(noticeVOList,userVOList,activity);
    }
}
