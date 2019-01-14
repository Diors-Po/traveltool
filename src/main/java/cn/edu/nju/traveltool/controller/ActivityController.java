package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.ActivityInfoVO;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.ActivityWithUserVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.service.ActivityService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-12 20:12
 **/
@RestController
@RequestMapping("activity")
@LoginRequired
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @GetMapping("list")
    public ReponseMessage<List<ActivityWithUserVO>> list(@CurrentUser User user){
        List<ActivityWithUserVO> activityVOPage = activityService.listActivity(user);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }

    @GetMapping("list/owner")
    public ReponseMessage<List<ActivityWithUserVO>> owner(@CurrentUser User user){
        List<ActivityWithUserVO>  activityVOPage = activityService.listActivity(user, ActivityWithUser.Status.OWNER);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }
    @GetMapping("list/member")
    public ReponseMessage<List<ActivityWithUserVO>> member(@CurrentUser User user){
        List<ActivityWithUserVO>  activityVOPage = activityService.listActivity(user, Lists.newArrayList(ActivityWithUser.Status.MEMBER, ActivityWithUser.Status.PREMEMBER, ActivityWithUser.Status.UNMEMBER));
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }

    @GetMapping("{activityId}/info")
    public ReponseMessage<ActivityInfoVO> info(@CurrentUser User user,@PathVariable("activityId")long activityId){
        ActivityInfoVO activityInfoVO = activityService.activityInfo(user,activityId);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityInfoVO);

    }
    @PostMapping("save")
    public ReponseMessage save(@CurrentUser User user,@RequestBody ActivityVO activityVO){
        activityVO.setUserId(user.getId());
        activityService.insertActivity(activityVO);
        return ReponseMessage.OK;
    }

    @PostMapping("modify")
    public ReponseMessage modify(@RequestBody ActivityVO activityVO){
        activityService.updateActivity(activityVO);
        return ReponseMessage.OK;
    }

    @PostMapping("join")
    public ReponseMessage join(@CurrentUser User user,@RequestBody JoinActivityVO joinActivityVO){
        joinActivityVO.setUserId(user.getId());
        activityService.modifyUserActivity(joinActivityVO,ActivityWithUser.Status.PREMEMBER);
        return ReponseMessage.OK;
    }

    @PostMapping("exit")
    public ReponseMessage exit(@CurrentUser User user,@RequestBody JoinActivityVO joinActivityVO){
        joinActivityVO.setUserId(user.getId());
        activityService.modifyUserActivity(joinActivityVO, ActivityWithUser.Status.EXITMEMBER);
        return ReponseMessage.OK;
    }
}
