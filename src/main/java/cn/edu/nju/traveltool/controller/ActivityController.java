package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.controller.vo.ActivityWithUserVO;
import cn.edu.nju.traveltool.controller.vo.JoinActivityVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.ActivityWithUser;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("list/{page}/{size}")
    public ReponseMessage<Page<ActivityWithUserVO>> list(@CurrentUser User user, @PathVariable("page")int page, @PathVariable("size")int size){
        Page<ActivityWithUserVO>  activityVOPage = activityService.listActivity(page, size,user);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVOPage);
    }
    @PostMapping("save")
    public ReponseMessage save(@RequestBody ActivityVO activityVO){
        activityService.insertActivity(activityVO);
        return ReponseMessage.OK;
    }

    @PostMapping("modify")
    public ReponseMessage modify(@RequestBody ActivityVO activityVO){
        activityService.updateActivity(activityVO);
        return ReponseMessage.OK;
    }

    @PostMapping("join")
    public ReponseMessage join(@RequestBody JoinActivityVO joinActivityVO){
        activityService.joinActivity(joinActivityVO);
        return ReponseMessage.OK;
    }
}
