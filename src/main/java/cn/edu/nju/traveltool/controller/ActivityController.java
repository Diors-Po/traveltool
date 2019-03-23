package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.ActivityInfoVO;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.ActivityWithUserVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.data.UserDTO;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.service.ActivityService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
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
    @Autowired
    private UserWrapper userWrapper;
    @GetMapping("list")
    public ReponseMessage<List<ActivityWithUserVO>> list(@CurrentUser UserDTO user){
        List<ActivityWithUserVO> activityVOPage = activityService.listActivity(userWrapper.unwrapperFromDTO(user));
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }

    @GetMapping("list/owner")
    public ReponseMessage<List<ActivityWithUserVO>> owner(@CurrentUser UserDTO user){
        List<ActivityWithUserVO>  activityVOPage = activityService.listActivity(userWrapper.unwrapperFromDTO(user), ActivityWithUser.Status.OWNER);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }
    @GetMapping("list/member")
    public ReponseMessage<List<ActivityWithUserVO>> member(@CurrentUser UserDTO user){
        List<ActivityWithUserVO>  activityVOPage = activityService.listActivity(userWrapper.unwrapperFromDTO(user), Lists.newArrayList(ActivityWithUser.Status.MEMBER, ActivityWithUser.Status.PREMEMBER, ActivityWithUser.Status.UNMEMBER));
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }

    @GetMapping("list/{activityId}/users")
    public ReponseMessage<List<ActivityWithUserVO>> users(@CurrentUser UserDTO user, @PathVariable("activityId")long activityId){
        List<ActivityWithUserVO>  userVOS = activityService.listUsersByActivityId(userWrapper.unwrapperFromDTO(user),activityId);

        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,userVOS);
    }

    @GetMapping("{activityId}/info")
    public ReponseMessage<ActivityInfoVO> info(@CurrentUser UserDTO user,@PathVariable("activityId")long activityId){
        ActivityInfoVO activityInfoVO = activityService.activityInfo(userWrapper.unwrapperFromDTO(user),activityId);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityInfoVO);

    }
    @PostMapping("save")
    public ReponseMessage save(@CurrentUser UserDTO user,@RequestBody ActivityVO activityVO){
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
    public ReponseMessage join(@CurrentUser UserDTO user,@RequestBody JoinActivityVO joinActivityVO){
        joinActivityVO.setUserId(user.getId());
        activityService.modifyUserActivity(joinActivityVO,ActivityWithUser.Status.PREMEMBER);
        return ReponseMessage.OK;
    }

    @PostMapping("exit")
    public ReponseMessage exit(@CurrentUser UserDTO user,@RequestBody JoinActivityVO joinActivityVO){
        joinActivityVO.setUserId(user.getId());
        activityService.modifyUserActivity(joinActivityVO, ActivityWithUser.Status.EXITMEMBER);
        return ReponseMessage.OK;
    }

    @PostMapping("close/{activityId}")
    public ReponseMessage close(@CurrentUser UserDTO user,@PathVariable("activityId")long activityId) {
        boolean flag = activityService.closedActivity(user.getId(),activityId);
        if(flag)
            return ReponseMessage.OK;
        else
            return new ReponseMessage(Constant.FAIL,Constant.REQUEST_FAIL);
    }

    @PostMapping("agree/{activityId}/{userId}")
    public ReponseMessage agree(@PathVariable("activityId")long activityId,@PathVariable("userId")long userId){
        activityService.modifyUserActivity(activityId,userId, ActivityWithUser.Status.MEMBER);
        return ReponseMessage.OK;
    }

    @PostMapping("reject/{activityId}/{userId}")
    public ReponseMessage reject(@PathVariable("activityId")long activityId,@PathVariable("userId")long userId){
        activityService.modifyUserActivity(activityId,userId, ActivityWithUser.Status.UNMEMBER);
        return ReponseMessage.OK;
    }
}
