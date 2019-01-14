package cn.edu.nju.traveltool.service.impl;

import cn.edu.nju.traveltool.controller.vo.ActivityListVO;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.ActivityWithUserVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.entity.Activity;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.repository.ActivityRespository;
import cn.edu.nju.traveltool.repository.ActivityWithUserRepository;
import cn.edu.nju.traveltool.service.ActivityService;
import cn.edu.nju.traveltool.wrapper.ActivityWithUserWrapper;
import cn.edu.nju.traveltool.wrapper.ActivityWrapper;
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
}
