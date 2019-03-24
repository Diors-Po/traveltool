package cn.edu.nju.traveltool.controller;

import cn.edu.nju.traveltool.annotation.CurrentUser;
import cn.edu.nju.traveltool.annotation.LoginRequired;
import cn.edu.nju.traveltool.constant.Constant;
import cn.edu.nju.traveltool.controller.vo.TopicVO;
import cn.edu.nju.traveltool.controller.vo.TopicWithUserVO;
import cn.edu.nju.traveltool.data.ReponseMessage;
import cn.edu.nju.traveltool.data.UserDTO;
import cn.edu.nju.traveltool.service.TopicService;
import cn.edu.nju.traveltool.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: traveltool
 * @mail: menduo96@gmail.com
 * @author: menduo
 * @create: 2019-01-14 15:07
 **/
@RestController
@LoginRequired
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserWrapper userWrapper;

    @GetMapping("list/{activityId}")
    public ReponseMessage<List<TopicWithUserVO>> list(@PathVariable("activityId")long activityId) {
        return new ReponseMessage<>(Constant.OK,Constant.REQUEST_SUCCESS,topicService.getTopicList(activityId));
    }

    @PostMapping("save")
    public ReponseMessage save(@CurrentUser UserDTO user, @RequestBody TopicVO topicVO){
        topicService.saveTopic(topicVO,userWrapper.unwrapperFromDTO(user));
        return ReponseMessage.OK;
    }

    @PostMapping("delete/{id}")
    public ReponseMessage delete(@CurrentUser UserDTO user,@PathVariable("id")long id){
        int flag = topicService.deleteTopic(id,user.getId());
        if(flag==-1) return new ReponseMessage(Constant.FAIL,Constant.NOT_FOUND_DELETE_MSG);
        return ReponseMessage.OK;
    }
}
