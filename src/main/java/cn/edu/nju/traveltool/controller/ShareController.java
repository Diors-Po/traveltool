package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.ActivityInfoVO;
import cn.edu.nju.traveltool.controller.vo.ActivityVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.entity.User;
import cn.edu.nju.traveltool.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 22:51
 **/
@RestController
public class ShareController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("share/{activityId}")
    public ReponseMessage<ActivityVO> info(@PathVariable("activityId")long activityId){
        ActivityVO activityVO = activityService.activityInfo(activityId);
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,activityVO);
    }
}
